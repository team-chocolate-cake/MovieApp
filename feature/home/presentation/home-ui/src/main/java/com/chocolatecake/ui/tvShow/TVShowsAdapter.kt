package com.chocolatecake.ui.tvShow

import androidx.recyclerview.widget.DiffUtil
import com.chocolatecake.bases.BasePagingAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.ItemTvShowBinding
import com.chocolatecake.viewmodel.tv_shows.TVShowsListener
import com.chocolatecake.viewmodel.tv_shows.TVShowsUI

class TVShowsAdapter(listener: TVShowsListener) :
    BasePagingAdapter<TVShowsUI, ItemTvShowBinding>(Comparator, listener) {

    override val layoutId = R.layout.item_tv_show

    object Comparator : DiffUtil.ItemCallback<TVShowsUI>() {
        override fun areItemsTheSame(oldItem: TVShowsUI, newItem: TVShowsUI): Boolean {
            return oldItem.tvId == newItem.tvId
        }

        override fun areContentsTheSame(
            oldItem: TVShowsUI,
            newItem: TVShowsUI
        ): Boolean {
            return oldItem == newItem
        }
    }
}

