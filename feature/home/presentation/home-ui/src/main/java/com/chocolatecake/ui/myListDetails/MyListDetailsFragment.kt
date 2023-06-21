package com.chocolatecake.ui.myListDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.bases.MediaType
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentMyListDetailsBinding
import com.chocolatecake.ui.profile.ProfileFragmentDirections
import com.chocolatecake.viewmodel.myListDetails.MyListDetailsUiEvent
import com.chocolatecake.viewmodel.myListDetails.MyListDetailsUiState
import com.chocolatecake.viewmodel.myListDetails.MyListDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyListDetailsFragment :
    BaseFragment<FragmentMyListDetailsBinding, MyListDetailsUiState, MyListDetailsUiEvent>(){

    override val layoutIdFragment: Int = R.layout.fragment_my_list_details
    override val viewModel: MyListDetailsViewModel by viewModels()
    private lateinit var myListDetailsAdapter: MyListDetailsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        myListDetailsAdapter = MyListDetailsAdapter(mutableListOf(), viewModel)
        binding.recyclerViewMyListDetails.adapter = myListDetailsAdapter
    }


    override fun onEvent(event: MyListDetailsUiEvent) {
        when (event) {
            is MyListDetailsUiEvent.NavigateToMovieDetails -> {
                findNavController().navigate(
                    MyListDetailsFragmentDirections.actionMyListDetailsFragmentToMovieDetailsFragment(
                     movieId = event.movieId,
                    )
                )
            }
            else -> {}
        }
    }
}

