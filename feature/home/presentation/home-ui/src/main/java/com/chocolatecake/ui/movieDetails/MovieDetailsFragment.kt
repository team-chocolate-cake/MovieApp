package com.chocolatecake.movieapp.ui.movieDetails

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.movieapp.ui.movieDetails.ui_state.MovieDetailsUiEvent
import com.chocolatecake.movieapp.ui.movieDetails.ui_state.MovieDetailsUiState
import com.chocolatecake.ui.R
import com.chocolatecake.ui.databinding.FragmentMovieDetailsBinding
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment: BaseFragment<FragmentMovieDetailsBinding, MovieDetailsUiState, MovieDetailsUiEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_movie_details
    override val viewModel: MovieDetailsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectImageBackground()
    }

    override fun onEvent(event: MovieDetailsUiEvent) {

    }

    private fun selectImageBackground(){
        val nightModeFlags = this.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when(nightModeFlags){
            Configuration.UI_MODE_NIGHT_YES ->binding.ImageBackground.setBackgroundResource(com.chocolatecake.bases.R.drawable.movie_image_background_night)
            Configuration.UI_MODE_NIGHT_NO ->binding.ImageBackground.setBackgroundResource(com.chocolatecake.bases.R.drawable.movie_image_background_light)
            else->binding.ImageBackground.setBackgroundResource(com.chocolatecake.bases.R.drawable.movie_image_background_light)
        }
    }
}