package com.chocolatecake.ui.tvShow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.ItemTvShowBinding
import com.chocolatecake.viewmodel.tv_shows.TVShowsUI
import javax.inject.Inject

class TVShowsAdapter @Inject constructor() : PagingDataAdapter<TVShowsUI, TVShowsViewHolder>(Comparator) {

    override fun onBindViewHolder(holder: TVShowsViewHolder, position: Int) {
        getItem(position)?.let { tvShowsUI -> holder.bind(tvShowsUI) }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowsViewHolder {
        return TVShowsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_tv_show, parent, false
            )
        )
    }

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

class TVShowsViewHolder(private val binding: ItemTvShowBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(tvShowsUI: TVShowsUI) {
        binding.item = tvShowsUI
        binding.executePendingBindings()
    }
}
