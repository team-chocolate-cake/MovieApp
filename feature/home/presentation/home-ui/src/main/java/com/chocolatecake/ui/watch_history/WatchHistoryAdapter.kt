package com.chocolatecake.ui.watch_history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.WatchHistoryRecyclerViewCardBinding
import com.chocolatecake.ui.home.databinding.WatchHistoryRecyclerViewTitleBinding
import com.chocolatecake.viewmodel.watch_history.state_managment.MovieUiState
import com.chocolatecake.viewmodel.watch_history.state_managment.WatchHistoryOnEventListeners
import java.util.Date
import kotlin.math.abs

class WatchHistoryAdapter(
    private var items: List<WatchHistoryRecyclerItem>,
    listener: WatchHistoryOnEventListeners
) : BaseAdapter<WatchHistoryRecyclerItem>(items, listener) {
    override val layoutID = -1
    private val currentDate = Date()

    class TitleViewHolder(val binding: WatchHistoryRecyclerViewTitleBinding) :
        BaseViewHolder(binding)

    class CardViewHolder(val binding: WatchHistoryRecyclerViewCardBinding) :
        BaseViewHolder(binding)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TITLE_ITEM -> TitleViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.watch_history_recycler_view_title,
                    parent,
                    false
                )
            )

            CARD_ITEM -> CardViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.watch_history_recycler_view_card,
                    parent,
                    false
                )

            )

            else -> throw Exception("item not found")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is TitleViewHolder -> bindTitle(holder, items[position])
            is CardViewHolder -> bindCard(holder, items[position])
            else -> throw Exception("recycler item not found")
        }
    }

    private fun bindCard(holder: CardViewHolder, item: WatchHistoryRecyclerItem) {
        holder.binding.item = (item as WatchHistoryRecyclerItem.CardRecyclerItem).movie
    }

    private fun bindTitle(holder: TitleViewHolder, item: WatchHistoryRecyclerItem) {
        holder.binding.item = (item as WatchHistoryRecyclerItem.TitleRecyclerItem).title
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is WatchHistoryRecyclerItem.TitleRecyclerItem -> TITLE_ITEM
            is WatchHistoryRecyclerItem.CardRecyclerItem -> CARD_ITEM
        }
    }


    override fun setItems(newItems: List<WatchHistoryRecyclerItem>) {

        items = newItems
        super.setItems(newItems)
    }

    private fun createItems(moviesInDataBase: List<MovieUiState>): List<WatchHistoryRecyclerItem> {
        var latestDateFound: Date? = null
        val moviesForRecyclerView = mutableListOf<WatchHistoryRecyclerItem>()

        for (movie in moviesInDataBase) {
            if (isNotSameTitle(latestDateFound, movie)) {
                moviesForRecyclerView +=
                    WatchHistoryRecyclerItem.TitleRecyclerItem(composeTitle(movie.dateWatched))
                latestDateFound = movie.dateWatched
            }
            moviesForRecyclerView += WatchHistoryRecyclerItem.CardRecyclerItem(mapper.map(movie))
        }

        return moviesForRecyclerView
    }

    private fun isNotSameTitle(
        latestDateFound: Date?,
        movie: MovieInWatchHistoryEntity
    ): Boolean {
        return latestDateFound == null ||
                movie.dateWatched?.getDaysDifferenceCount(latestDateFound) != THE_SAME_DAY
    }

    private fun composeTitle(movieWatchedDate: Date?): String {
        if (movieWatchedDate == null) return ""
        return when (currentDate.getDaysDifferenceCount(movieWatchedDate)) {
            THE_SAME_DAY -> "Today"
            YESTERDAY -> "Yesterday"
            else -> movieWatchedDate.toString().substring(0..9)
        }
    }

    private fun Date.getDaysDifferenceCount(otherDate: Date): Int {
        val firstDateText = this.toString().substring(4..9)
        val otherDateText = otherDate.toString().substring(4..9)
        return when {
            firstDateText == otherDateText -> THE_SAME_DAY
            isOneDayDifference(firstDateText, otherDateText) -> YESTERDAY
            else -> OTHER_DAYS
        }
    }

    private fun isOneDayDifference(firstDateText: String, otherDateText: String): Boolean {
        val month1 = firstDateText.substring(0..2)
        val month2 = otherDateText.substring(0..2)
        val day1 = firstDateText.substring(4..5).toInt()
        val day2 = otherDateText.substring(4..5).toInt()

        return month1 == month2 && abs(day1 - day2) == 1
    }

    private companion object {
        const val THE_SAME_DAY = 0
        const val YESTERDAY = 1
        const val OTHER_DAYS = 1124
        const val TITLE_ITEM = 21
        const val CARD_ITEM = 14
    }

}