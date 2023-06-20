package com.chocolatecake.ui.showMore

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentShowMoreBinding
import com.chocolatecake.viewmodel.showmore.ShowAllUiState
import com.chocolatecake.viewmodel.showmore.ShowMoreType
import com.chocolatecake.viewmodel.showmore.ShowMoreUiEvent
import com.chocolatecake.viewmodel.showmore.ShowMoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShowMoreFragment : BaseFragment<FragmentShowMoreBinding, ShowAllUiState, ShowMoreUiEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_show_more
    override val viewModel: ShowMoreViewModel by viewModels()
    private  val showMoreAdapter by lazy{ShowMoreAdapter(viewModel)}


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {

        binding.recyclerMedia.adapter = showMoreAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                Log.e("TAG", "setAdapter: $state ", )
                val flow = when (state.showMoreType) {
                    ShowMoreType.POPULAR_MOVIES -> state.showMorePopularMovies
                    ShowMoreType.TOP_RATED -> state.showMoreTopRated
                    ShowMoreType.TRENDING -> state.showMoreTrending
                }
                collectLast(flow) { itemsPagingData ->
                    viewLifecycleOwner.lifecycleScope.launch {
                        showMoreAdapter.submitData(itemsPagingData)
                    }
                }
            }
        }
    }

    private fun <T> LifecycleOwner.collectLast(flow: Flow<T>, action: suspend (T) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collectLatest(action)
            }
        }
    }

    override fun onEvent(event: ShowMoreUiEvent) {
        when (event) {
            ShowMoreUiEvent.BackNavigate -> TODO()
            is ShowMoreUiEvent.NavigateToMovieDetails -> TODO()

            else -> {}
        }
    }

}


