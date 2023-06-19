package com.chocolatecake.ui.movieDetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentMovieDetailsBinding
import com.chocolatecake.ui.movieDetails.adapter.MovieDetailsAdapter
import com.chocolatecake.ui.movieDetails.adapter.MovieDetailsItem
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsUiEvent
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsUiState
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment :
    BaseFragment<FragmentMovieDetailsBinding, MovieDetailsUiState, MovieDetailsUiEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_movie_details
    override val viewModel: MovieDetailsViewModel by viewModels()

    private lateinit var movieDetailsAdapter: MovieDetailsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        binding.toolbar.title = ""
        setAdapter()
        collectChange()
    }

    private fun setAdapter() {
        movieDetailsAdapter = MovieDetailsAdapter(mutableListOf(), viewModel, viewModel, viewModel)
        binding.nestedRecycler.adapter = movieDetailsAdapter
    }

    private fun collectChange() {
        collectLatest {
            viewModel.state.collect { state ->
                movieDetailsAdapter.setItems(
                    mutableListOf(
                        MovieDetailsItem.Upper(state.movieUiState),
                        MovieDetailsItem.People(state.castUiState),
                        MovieDetailsItem.Recommended(
                            state.recommendedUiState,
                            state.reviewUiState.isEmpty()
                        ),

                        ) + state.reviewUiState.map { MovieDetailsItem.Reviews(it) }
                )
                binding.nestedRecycler.smoothScrollToPosition(0)
            }
        }
    }

    override fun onEvent(event: MovieDetailsUiEvent) {
        val bottomSheet = RatingMovieBottomSheet()
        when (event) {
            MovieDetailsUiEvent.OnClickBack -> {
                findNavController().popBackStack()
            }

            is MovieDetailsUiEvent.PeopleEvent -> {
                //todo
            }

            is MovieDetailsUiEvent.PlayVideoEvent -> {
                //todo
            }

            is MovieDetailsUiEvent.RateMovieEvent -> {
                val movieId = event.movieId
                bottomSheet.show(childFragmentManager, "BOTTOM")
                bottomSheet.setMovieID(movieId)
            }

            is MovieDetailsUiEvent.RecommendedMovieEvent -> {
                // findNavController().navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragmentSelf(event.movieId))
            }

            is MovieDetailsUiEvent.onSuccessRateEvent -> {
                //todo
            }

            is MovieDetailsUiEvent.SaveToEvent -> {
                //todo
            }

        }
    }
}