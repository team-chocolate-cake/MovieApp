package com.chocolatecake.ui.my_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentMyListBinding
import com.chocolatecake.viewmodel.myList.MyListUiEvent
import com.chocolatecake.viewmodel.myList.MyListUiState
import com.chocolatecake.viewmodel.myList.MyListViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyListFragment :
    BaseFragment<FragmentMyListBinding, MyListUiState, MyListUiEvent>(), CreateListener {

    override val layoutIdFragment: Int = R.layout.fragment_my_list
    override val viewModel: MyListViewModel by viewModels()
    private lateinit var createListBottomSheet: CreateListBottomSheetFragment

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
//                applyCreateList()
            }

            is MyListUiEvent.OpenCreateListBottomSheet -> {
                showBottomSheet()
            }

            is MyListUiEvent.OnClickBack -> {
                findNavController().popBackStack()
            }

            is MyListUiEvent.ShowSnackBar -> {
                showSnackBar(event.message)
            }

            is MyListUiEvent.OnCreateNewList -> {
                showSnackBar(event.message)
            }

            is MyListUiEvent.ShowConfirmDeleteDialog -> {
                showDialog(event.listId, event.listName)
            }
        }
    }

    private fun showDialog(listId: Int, listName: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete")
            .setMessage("Are you sure that you want to delete $listName?")
            .setPositiveButton("Confirm") { _, _ ->
                viewModel.deleteList(listId)
            }
            .setNeutralButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }


    private fun showBottomSheet() {
        createListBottomSheet = CreateListBottomSheetFragment(this)
        createListBottomSheet.show(childFragmentManager, "BOTTOM")
    }

    override fun onClickCreate(listName: String) {
        viewModel.onCreateList(listName)
        createListBottomSheet.dismiss()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData()
    }
}

