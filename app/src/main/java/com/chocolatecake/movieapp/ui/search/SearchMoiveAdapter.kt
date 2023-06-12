package com.chocolatecake.movieapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.databinding.SearchItemMovieBinding
import javax.inject.Inject

/*class TVShowsAdapter(MediaUIStateList: List<MediaUIState>, listener: TVShowsListener) :
    BaseAdapter<MediaUIState>(MediaUIStateList, listener) {
    override val layoutID = R.layout.tv_show_item
}*/

class SearchMovieAdapter @Inject constructor() :
    PagingDataAdapter<SearchUiState.MediaUIState, SearchMovieViewHolder>(Comparator) {

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        getItem(position)?.let { MediaUIState -> holder.bind(MediaUIState) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        return SearchMovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.search_item_movie, parent, false
            )
        )
    }

    object Comparator : DiffUtil.ItemCallback<SearchUiState.MediaUIState>() {
        override fun areItemsTheSame(
            oldItem: SearchUiState.MediaUIState,
            newItem: SearchUiState.MediaUIState
        ): Boolean {
            return oldItem.mediaId == newItem.mediaId
        }

        override fun areContentsTheSame(
            oldItem: SearchUiState.MediaUIState,
            newItem: SearchUiState.MediaUIState
        ): Boolean {
            return oldItem == newItem
        }
    }
}

class SearchMovieViewHolder(private val binding: SearchItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(MediaUIState: SearchUiState.MediaUIState) {
        binding.item = MediaUIState
    }
}
