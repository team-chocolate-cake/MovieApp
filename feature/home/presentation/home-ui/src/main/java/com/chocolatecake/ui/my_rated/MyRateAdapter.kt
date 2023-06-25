package com.chocolatecake.ui.my_rated

import androidx.recyclerview.widget.DiffUtil
import com.chocolatecake.bases.BasePagingAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.ItemMovieHorizontalBinding
import com.chocolatecake.ui.home.databinding.ItemTvShowBinding
import com.chocolatecake.viewmodel.common.model.MovieHorizontalUIState
import com.chocolatecake.viewmodel.my_rated.MyRatedListner
import com.chocolatecake.viewmodel.my_rated.MyRatedUiState
import com.chocolatecake.viewmodel.tv_shows.TVShowsListener
import com.chocolatecake.viewmodel.tv_shows.TVShowsUI

class MyRateAdapter(listener: MyRatedListner) :
    BasePagingAdapter<MovieHorizontalUIState, ItemMovieHorizontalBinding>(Comparator, listener) {

    override val layoutId = R.layout.item_movie_horizontal

    object Comparator : DiffUtil.ItemCallback<MovieHorizontalUIState>() {
        override fun areItemsTheSame(oldItem: MovieHorizontalUIState, newItem: MovieHorizontalUIState): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieHorizontalUIState,
            newItem: MovieHorizontalUIState
        ): Boolean {
            return oldItem == newItem
        }
    }
}

