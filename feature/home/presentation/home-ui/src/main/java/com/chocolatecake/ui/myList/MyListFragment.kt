package com.chocolatecake.ui.myList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentMyListBinding
import com.chocolatecake.ui.myListDetails.MyListDetailsFragmentDirections
import com.chocolatecake.ui.search.FilterMovieBottomSheetFragment
import com.chocolatecake.viewmodel.myList.MyListUiEvent
import com.chocolatecake.viewmodel.myList.MyListUiState
import com.chocolatecake.viewmodel.myList.MyListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyListFragment :
    BaseFragment<FragmentMyListBinding, MyListUiState, MyListUiEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_my_list
    override val viewModel: MyListViewModel by viewModels()


    private lateinit var myListAdapter: MyListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        myListAdapter = MyListAdapter(mutableListOf(), viewModel)
        binding.recyclerViewMyList.adapter = myListAdapter
    }


    override fun onEvent(event: MyListUiEvent) {
        when (event) {
            is MyListUiEvent.NavigateToListDetails -> {
                findNavController().navigate(
                    MyListFragmentDirections.actionMyListFragmentToMyListDetailsFragment(
                        listType = event.listType,
                        listId = event.listId,
                        listName = event.listName,
                    )
                )
            }

            is MyListUiEvent.ApplyCreateList -> {
                applyCreateList()
            }

            is MyListUiEvent.OpenCreateListBottomSheet -> {
                showBottomSheet()
            }

            is MyListUiEvent.OnClickBack ->{
                findNavController().popBackStack()
            }

            is MyListUiEvent.ShowSnackBar -> {
                showSnackBar(event.message)
            }

            else -> {}
        }
    }


    private fun applyCreateList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.onCreateList()
        }
    }

    private fun showBottomSheet() {
        CreateListBottomSheetFragment().show(childFragmentManager, "BOTTOM")
    }
}

