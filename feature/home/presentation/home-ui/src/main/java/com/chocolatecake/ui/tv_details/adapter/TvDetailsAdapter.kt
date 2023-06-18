package com.chocolatecake.ui.tv_details.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.common.adapters.PeopleAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.ItemSeasonHorizontalBinding
import com.chocolatecake.ui.home.databinding.TvDetailsItemPeopleRvBinding
import com.chocolatecake.ui.home.databinding.TvDetailsItemUpperBinding
import com.chocolatecake.ui.tv_details.TvDetailsItem
import com.chocolatecake.viewmodel.tv_details.TvDetailsListeners
import com.chocolatecake.ui.tv_details.TvDetailsType

class TvDetailsAdapter(
    private var tvDetailsItems: MutableList<TvDetailsItem>,
    private val listener: TvDetailsListeners
) : BaseAdapter<TvDetailsItem>(tvDetailsItems, listener) {
    override val layoutID: Int = 4

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TvDetailsType.UPPER.ordinal -> UpperViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.tv_details_item_upper, parent, false
                )
            )

            TvDetailsType.PEOPLE.ordinal -> PeopleViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.tv_details_item_people_rv, parent, false
                )
            )

            TvDetailsType.Seasons.ordinal -> SeasonViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_season_horizontal, parent, false
                )
            )


            else -> throw IllegalStateException("Unkown ID ${parent}")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is UpperViewHolder -> bindUpper(holder, position)
            is PeopleViewHolder -> bindPeople(holder, position)
            is SeasonViewHolder -> bindSeason(holder, position)
        }
    }

    fun setItem(item: TvDetailsItem) {
        val newItems = tvDetailsItems.apply {
            removeAt(item.type.ordinal)
            add(item.type.ordinal, item)
        }
        setItems(newItems)
    }

    override fun setItems(newItems: List<TvDetailsItem>) {
        tvDetailsItems = newItems.sortedBy { it.type.ordinal }.toMutableList()
        super.setItems(newItems)
    }

    override fun getItemViewType(position: Int): Int = tvDetailsItems[position].type.ordinal
    private fun bindPeople(holder: PeopleViewHolder, position: Int) {
        val people = tvDetailsItems[position] as TvDetailsItem.People
        val adapter = PeopleAdapter(people.people, listener)
        holder.binding.recyclerViewPeople.adapter = adapter
        Log.i("people","people are $people")
        holder.binding.item = people
    }

    private fun bindSeason(holder: SeasonViewHolder, position: Int) {
        val season = tvDetailsItems[position] as TvDetailsItem.Season
        Log.i("people","seasons are $season")
        holder.binding.item = season.season
        holder.binding.listener = listener
    }

    private fun bindUpper(holder: UpperViewHolder, position: Int) {
        val upper = tvDetailsItems[position] as TvDetailsItem.Upper
        holder.binding.item = upper
    }

    class UpperViewHolder(val binding: TvDetailsItemUpperBinding) : BaseViewHolder(binding)

    class PeopleViewHolder(val binding: TvDetailsItemPeopleRvBinding) :
        BaseViewHolder(binding)

    class SeasonViewHolder(val binding: ItemSeasonHorizontalBinding) : BaseViewHolder(binding)
}
