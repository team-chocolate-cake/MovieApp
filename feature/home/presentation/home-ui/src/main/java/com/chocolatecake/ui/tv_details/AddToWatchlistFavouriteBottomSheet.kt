package com.chocolatecake.ui.tv_details

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.MyListBottomSheetCreateListBinding
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiEvent
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiState
import com.chocolatecake.viewmodel.tv_details.TvDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddToWatchlistFavouriteBottomSheet(private val watchlistFavouriteBottomSheet: WatchlistFavouriteListener,) :
    BaseBottomSheet<MyListBottomSheetCreateListBinding>() {
    override val layoutIdFragment: Int= R.layout.my_list_bottom_sheet_create_list
    override val viewModel by activityViewModels<TvDetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chipAddNewList.visibility = View.GONE
        binding.apply {
            viewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
            chipAddNewList.setOnClickListener {
                groupCreateList.visibility =
                    if (chipAddNewList.isChecked) View.VISIBLE else View.GONE
            }
            textViewClose.setOnClickListener {
                dismiss()
            }
        }
        viewModel.getUserLists()
        binding.textViewDone.setOnClickListener {
            if (binding.chipFavourite.isChecked) watchlistFavouriteBottomSheet.onFavourite()
            if (binding.chipWatchlist.isChecked) watchlistFavouriteBottomSheet.onWatchlist()
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        watchlistFavouriteBottomSheet.onDismiss()
    }
}

interface WatchlistFavouriteListener {
    fun onFavourite()
    fun onWatchlist()
    fun onDismiss()
}