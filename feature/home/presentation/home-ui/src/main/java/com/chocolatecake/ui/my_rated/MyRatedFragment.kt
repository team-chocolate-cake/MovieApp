package com.chocolatecake.ui.my_rated

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chocolatecake.bases.BaseFooterAdapter
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentMyRatedBinding
import com.chocolatecake.ui.tvShow.TVShowsAdapter
import com.chocolatecake.ui.tvShow.collectLast
import com.chocolatecake.ui.tvShow.setSpanSize
import com.chocolatecake.viewmodel.my_rated.MyRateType
import com.chocolatecake.viewmodel.my_rated.MyRatedEvents
import com.chocolatecake.viewmodel.my_rated.MyRatedUiState
import com.chocolatecake.viewmodel.my_rated.MyRatedViewModel
import com.chocolatecake.viewmodel.tv_shows.TVShowsType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyRatedFragment : BaseFragment<FragmentMyRatedBinding , MyRatedUiState , MyRatedEvents>() {

    override val layoutIdFragment: Int = R.layout.fragment_my_rated
    override val viewModel: MyRatedViewModel by viewModels()
    private val myRateAdapter by lazy { MyRateAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }
    private fun setAdapter() {
        val footerAdapter = BaseFooterAdapter { myRateAdapter.retry() }
        binding.recyclerViewMedia.adapter = myRateAdapter.withLoadStateFooter(footerAdapter)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                val flow = state.MyRatedMedia
                collectLast(flow) { itemsPagingData ->
                    viewLifecycleOwner.lifecycleScope.launch {
                        myRateAdapter.submitData(itemsPagingData)
                    }
                }
                collectLast(myRateAdapter.loadStateFlow) { viewModel.setErrorUiState(it) }
            }
        }
    }

    override fun onEvent(event: MyRatedEvents) {
        when(event){
            is MyRatedEvents.NavigateToMovieDetails -> findNavController().navigate(MyRatedFragmentDirections.actionMyRatedFragmentToMovieDetailsFragment(event.movieId))
            is MyRatedEvents.NavigateToTVShowDetails -> findNavController().navigate(MyRatedFragmentDirections.actionMyRatedFragmentToMovieDetailsFragment(event.tvId))
            is MyRatedEvents.OnBackPressed -> findNavController().popBackStack()
            is MyRatedEvents.ShowMyRatedMoviesPressed -> viewModel.getMyRatedMovies()
            is MyRatedEvents.ShowMyRatedTvShowPressed -> viewModel.getMyRatedTvShow()
        }
    }


}