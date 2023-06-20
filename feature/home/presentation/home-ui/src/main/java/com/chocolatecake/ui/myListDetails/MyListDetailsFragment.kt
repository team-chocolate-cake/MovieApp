package com.chocolatecake.ui.myListDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentMyListDetailsBinding
import com.chocolatecake.viewmodel.myListDetails.MyListDetailsUiEvent
import com.chocolatecake.viewmodel.myListDetails.MyListDetailsUiState
import com.chocolatecake.viewmodel.myListDetails.MyListDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyListDetailsFragment :
    BaseFragment<FragmentMyListDetailsBinding, MyListDetailsUiState, MyListDetailsUiEvent>(){

    override val layoutIdFragment: Int = R.layout.fragment_my_list_details
    override val viewModel: MyListDetailsViewModel by viewModels()
//    private val args: MyListDetailsFragmentArgs by navArgs()
    private lateinit var myListDetailsAdapter: MyListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        collectChange()
    }

    private fun setAdapter() {
        myListDetailsAdapter = MyListAdapter(mutableListOf(), viewModel)
        binding.recyclerViewMyListDetails.adapter = myListDetailsAdapter
    }

    private fun collectChange() {
        collectLatest {
            viewModel.state.collect { state ->
                myListDetailsAdapter
            }
        }
    }


    override fun onEvent(event: MyListDetailsUiEvent) {
        when (event) {
            is MyListDetailsUiEvent.NavigateToMovieDetails -> TODO()
            else -> {

            }
        }
    }
}

