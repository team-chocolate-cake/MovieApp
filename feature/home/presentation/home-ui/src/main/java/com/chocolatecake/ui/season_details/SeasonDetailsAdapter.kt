package com.chocolatecake.ui.season_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentSeasonDetailsBinding
import com.chocolatecake.ui.home.databinding.ItemEpisodeHorizontalBinding
import com.chocolatecake.ui.home.databinding.ItemSeasonDetailsHeaderBinding
import com.chocolatecake.viewmodel.common.listener.EpisodeListener

class SeasonDetailsAdapter (
    private var list: MutableList<SeasonDetailsItem>,
    private val listener: EpisodeListener
): BaseAdapter<SeasonDetailsItem>(list, listener) {
    override val layoutID: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType){
            SeasonDetailsType.OVERVIEW.ordinal -> {
                OverviewViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                    R.layout.item_season_details_header,parent,false))
            }
            SeasonDetailsType.EPISODE.ordinal -> {
                EpisodeViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_episode_horizontal,parent,false))
            }
            else -> throw Exception("UNKNOWN VIEW HOLDER")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when(holder){
            is OverviewViewHolder -> bindOverview(holder, position)
            is EpisodeViewHolder -> bindEpisodes(holder, position)
        }
    }

    private fun bindOverview(holder: OverviewViewHolder, position: Int){
        val overview = list[position] as SeasonDetailsItem.OverviewItem
        holder.binding.item = overview.overview
    }

    private fun bindEpisodes(holder: EpisodeViewHolder, position: Int){
        val episode = list[position] as SeasonDetailsItem.EpisodeItem
        holder.binding.item = episode.episodeHorizontalUIState
        holder.binding.listener = listener
    }

    fun setItem(item: SeasonDetailsItem){
        val newItems = list.apply {
            removeAt(item.type.ordinal)
            add(item.type.ordinal, item)
        }
        setItems(newItems)
    }

    override fun setItems(newItems: List<SeasonDetailsItem>) {
        list = newItems.sortedBy { it.type.ordinal }.toMutableList()
        super.setItems(newItems)
    }

    override fun getItemViewType(position: Int): Int = list[position].type.ordinal

    class OverviewViewHolder(val binding: ItemSeasonDetailsHeaderBinding): BaseViewHolder(binding)

    class EpisodeViewHolder(val binding: ItemEpisodeHorizontalBinding): BaseViewHolder(binding)
}