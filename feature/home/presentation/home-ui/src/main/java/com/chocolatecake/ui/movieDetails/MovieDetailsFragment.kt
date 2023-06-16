package com.chocolatecake.ui.movieDetails

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment

import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.adapter.HomeAdapter
import com.chocolatecake.ui.home.databinding.FragmentMovieDetailsBinding
import com.chocolatecake.ui.movieDetails.adapter.MovieDetailsAdapter
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsViewModel
import com.chocolatecake.viewmodel.movieDetails.ui_state.MovieDetailsUiEvent
import com.chocolatecake.viewmodel.movieDetails.ui_state.MovieDetailsUiState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment: BaseFragment<FragmentMovieDetailsBinding, MovieDetailsUiState, MovieDetailsUiEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_movie_details
    override val viewModel: MovieDetailsViewModel by viewModels()

    private lateinit var movieDetailsAdapter: MovieDetailsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectImageBackground()
        setAdapter()
        collectChange()
    }

    private fun setAdapter() {
        movieDetailsAdapter = MovieDetailsAdapter(mutableListOf(), viewModel)
        binding.nestedRecycler.adapter = movieDetailsAdapter
    }

    private fun collectChange() {
        collectLatest {
            viewModel.state.collect { state ->
                movieDetailsAdapter.setItems(
                    mutableListOf(
                        state.castUiState,
                        state.recommendedUiState,
                        state.reviewUiState
                    )
                )

            }

        }
        binding.nestedRecycler.smoothScrollToPosition(0)
    }

    override fun onEvent(event: MovieDetailsUiEvent) {
        when(event){
            MovieDetailsUiEvent.OnClickBack -> {
                TODO()
            }
            is MovieDetailsUiEvent.PlayVideoEvent -> {
                TODO()
            }
            is MovieDetailsUiEvent.RateMovieEvent -> {
                TODO()
            }
            is MovieDetailsUiEvent.SaveToEvent -> {
                TODO()
            }
            else -> {}
        }
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