package com.chocolatecake.ui.movieDetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentMovieDetailsBinding
import com.chocolatecake.ui.movieDetails.adapter.MovieDetailsAdapter
import com.chocolatecake.ui.movieDetails.adapter.MovieDetailsItem
import com.chocolatecake.ui.tv_details.AddToListBottomSheet
import com.chocolatecake.ui.tv_details.CreateListener
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsUiEvent
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsUiState
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs


@AndroidEntryPoint
class MovieDetailsFragment :
    BaseFragment<FragmentMovieDetailsBinding, MovieDetailsUiState, MovieDetailsUiEvent>(),
    BottomSheetDismissListener, CreateListener {

    override val layoutIdFragment: Int = R.layout.fragment_movie_details
    override val viewModel: MovieDetailsViewModel by viewModels()

    private lateinit var movieDetailsAdapter: MovieDetailsAdapter
    private lateinit var addToListBottomSheet: AddToListBottomSheet
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        binding.toolbar.title = ""
        setAdapter()
        collectChange()
        collapseState()
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
                            state.reviewUiState.isEmpty(),
                            state.id,
                            state.reviewsDetails.totalReviews,
                            state.reviewsDetails.totalPages > 1
                        ),

                        ) + state.reviewUiState.map { MovieDetailsItem.Reviews(it) }
                )
                binding.nestedRecycler.smoothScrollToPosition(0)
            }
        }
    }

    override fun onEvent(event: MovieDetailsUiEvent) {
        when (event) {
            MovieDetailsUiEvent.OnClickBack -> {
                findNavController().popBackStack()
            }

            is MovieDetailsUiEvent.NavigateToPeopleDetails -> {
                findNavController().navigate(
                    MovieDetailsFragmentDirections.actionMovieDetailsFragmentToPeopleDetailsFragment(
                        event.itemId
                    )
                )
            }

            is MovieDetailsUiEvent.PlayVideoTrailer -> {
                //todo
            }

            is MovieDetailsUiEvent.RateMovie -> {
                showRatingBottomSheet(event.movieId)
            }

            is MovieDetailsUiEvent.NavigateToMovie -> {
                findNavController().navigate(
                    MovieDetailsFragmentDirections.actionMovieDetailsFragmentSelf(
                        event.movieId
                    )
                )
            }

            is MovieDetailsUiEvent.SaveToList -> showAddToListBottomSheet()

            is MovieDetailsUiEvent.NavigateToShowMore -> {
                //todo
            }

            is MovieDetailsUiEvent.ApplyRating -> showSnackBar(event.message)

            is MovieDetailsUiEvent.OnDoneAdding -> showSnackBar(event.message)
            is MovieDetailsUiEvent.OnFavourite -> showSnackBar(event.message)
            is MovieDetailsUiEvent.OnWatchList -> showSnackBar(event.message)
            else -> {}
        }
    }

    private fun showRatingBottomSheet(movieId: Int) {
        val bottomSheet = RatingMovieBottomSheet()
        bottomSheet.show(childFragmentManager, "BOTTOM")
        bottomSheet.setListener(this)
    }

    override fun onApplyRateBottomSheet() {
        viewModel.onRatingSubmit()
    }

    override fun updateRatingValue(rate: Float) {
        viewModel.updateRatingUiState(rate)
    }

    private fun collapseState() {
        var pos = 0
        findNavController().addOnDestinationChangedListener { _, destination, _ ->
            binding.nestedRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val firstVisibleItemPosition = recyclerView.layoutManager as LinearLayoutManager
                    pos = firstVisibleItemPosition.findFirstVisibleItemPosition()
                }
            })
            binding.appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                when {
                    // Fully expanded state
                    verticalOffset == 0 -> {
                        binding.textViewToolBarName.visibility = View.INVISIBLE
                        if (pos != 0) appBarLayout.setExpanded(false, false)
                    }
                    // Fully collapsed state
                    abs(verticalOffset) >= appBarLayout.totalScrollRange -> {
                        binding.textViewToolBarName.visibility = View.VISIBLE
                    }
                    // In between expanded and collapsed states
                    else -> {
                        binding.textViewToolBarName.visibility = View.INVISIBLE
                    }
                }
            }

        }
    }

    private fun showAddToListBottomSheet() {
        addToListBottomSheet = AddToListBottomSheet(this)
        addToListBottomSheet.show(childFragmentManager, "BOTTOM")
    }

    override fun onClickCreate(listName: String) {
        viewModel.createUserNewList(listName)
    }

    override fun onDone(listsId: List<Int>) {
        viewModel.onDone(listsId)
    }

    override fun onFavourite() {
        viewModel.addToFavourite()
    }

    override fun onWatchlist() {
        viewModel.addToWatchlist()
    }
}