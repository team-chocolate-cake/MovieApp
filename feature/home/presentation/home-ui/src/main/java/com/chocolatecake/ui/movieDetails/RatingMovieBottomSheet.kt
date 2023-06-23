package com.chocolatecake.ui.movieDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.MovieRatingBottomSheetBinding
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RatingMovieBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: MovieRatingBottomSheetBinding
    private val viewModel by activityViewModels<MovieDetailsViewModel>()
    private var dismissListener: BottomSheetDismissListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.movie_rating_bottom_sheet,
                container,
                false
            )
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
            return root
        }
    }

    fun setListener(dismissListener: BottomSheetDismissListener) {
        this.dismissListener = dismissListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        var userRating = 0f
        binding.rating.setOnRatingBarChangeListener { _, rating, _ ->
            // Access the rating value here
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