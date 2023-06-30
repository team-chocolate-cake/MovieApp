package com.chocolatecake.ui.movie_details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.MovieRatingBottomSheetBinding
import com.chocolatecake.ui.tv_details.BaseBottomSheet
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RatingMovieBottomSheet : BaseBottomSheet<MovieRatingBottomSheetBinding>() {
    override val layoutIdFragment: Int = R.layout.movie_rating_bottom_sheet
    override val viewModel by activityViewModels<MovieDetailsViewModel>()
    private var dismissListener: BottomSheetDismissListener? = null

    fun setListener(dismissListener: BottomSheetDismissListener) {
        this.dismissListener = dismissListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        var userRating = 0f


        binding.movieRatingBar.rating = viewModel.state.value.movieUiState.voteAverage

        Log.d("rate", viewModel.state.value.movieUiState.voteAverage.toString())

        binding.movieRatingBar.setOnRatingBarChangeListener { _, rating, _ ->
            userRating = rating * 2

            Log.i("rate", "$userRating")
        }
        binding.buttonApply.setOnClickListener {
            if (userRating == 0f) {
                showSnackBar("Please Rate First")
            } else {
                dismissListener?.onApplyRateBottomSheet()
                dismissListener?.updateRatingValue(userRating)
                dismiss()
            }
        }
    }

    private fun showSnackBar(messages: String) {
        Snackbar.make(binding.root, messages, Snackbar.LENGTH_SHORT).show()
    }
}

interface BottomSheetDismissListener {
    fun onApplyRateBottomSheet()
    fun updateRatingValue(rate: Float)
}