package com.chocolatecake.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.ItemMovieHorizontalBinding
import com.chocolatecake.ui.home.databinding.ItemPeopleBinding
import com.chocolatecake.viewmodel.search.SearchItem
import com.chocolatecake.viewmodel.search.SearchItemType
import com.chocolatecake.viewmodel.search.SearchListener

class SearchAdapter(
    private var list: MutableList<SearchItem>,
    private val listener: SearchListener
) : BaseAdapter<SearchItem>(list, listener) {
    override val layoutID: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            SearchItemType.MEDIA.ordinal -> {
                MediaViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_movie_horizontal, parent, false
                    )
                )
            }

            SearchItemType.PEOPLE.ordinal -> {
                PeopleViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_people, parent, false
                    )
                )
            }

            else -> throw Exception("UNKNOWN VIEW HOLDER")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is MediaViewHolder -> bindMedia(holder, position)
            is PeopleViewHolder -> bindPeople(holder, position)
        }
    }

    private fun bindMedia(holder: MediaViewHolder, position: Int) {
        val media = list[position] as SearchItem.MediaItem
        media.movieHorizontalUIState.forEach { holder.binding.item = it }
        holder.binding.listener = listener

    }

    private fun bindPeople(holder: PeopleViewHolder, position: Int) {
        val people = list[position] as SearchItem.PeopleItem
        people.peopleItem.forEach { holder.binding.item = it }
        holder.binding.listener = listener
    }

    override fun setItems(newItems: List<SearchItem>) {
        list = newItems.sortedBy { it.type.ordinal }.toMutableList()
        super.setItems(newItems)
    }


    override fun getItemViewType(position: Int): Int = list[position].type.ordinal

    class MediaViewHolder(val binding: ItemMovieHorizontalBinding) : BaseViewHolder(binding)

    class PeopleViewHolder(val binding: ItemPeopleBinding) : BaseViewHolder(binding)

}