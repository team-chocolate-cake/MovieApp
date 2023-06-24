package com.chocolatecake.ui.tv_details

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.chocolatecake.ui.home.BR
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.MyListBottomSheetCreateListBinding
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddToWatchlistFavouriteBottomSheet(private val watchlistFavouriteBottomSheet: WatchlistFavouriteListener) :
    BottomSheetDialogFragment() {
    private lateinit var binding: MyListBottomSheetCreateListBinding
    val viewModel by activityViewModels<MovieDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.my_list_bottom_sheet_create_list,
                container,
                false
            )
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
            return root
        }
    }


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