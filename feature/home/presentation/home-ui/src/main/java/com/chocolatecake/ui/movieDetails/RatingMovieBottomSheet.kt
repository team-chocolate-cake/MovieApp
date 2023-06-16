package com.chocolatecake.ui.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.MovieRatingBottomSheetBinding
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RatingMovieBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: MovieRatingBottomSheetBinding
    private val viewModel by activityViewModels<MovieDetailsViewModel>()
    private var movieId:Int = 0


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

    fun setMovieID(id:Int){
        movieId = id
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.buttonApply.setOnClickListener{
            viewModel.onRatingSubmit(binding.rating.rating , movieId)
            Toast.makeText(this.context , "Thanks for rating <3" , Toast.LENGTH_SHORT).show()
            this.dismiss()
        }
    }
}