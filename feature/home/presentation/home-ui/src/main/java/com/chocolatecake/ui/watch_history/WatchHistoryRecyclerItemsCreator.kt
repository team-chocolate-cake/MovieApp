package com.chocolatecake.ui.watch_history

import com.chocolatecake.viewmodel.watch_history.state_managment.MovieUiState
import java.util.Date
import kotlin.math.abs

class WatchHistoryRecyclerItemsCreator {
    private val currentDate = Date()

     fun createItems(moviesInDataBase: List<MovieUiState>): List<WatchHistoryRecyclerItem> {
        var latestDateFound: Date? = null
        val moviesForRecyclerView = mutableListOf<WatchHistoryRecyclerItem>()

        for (movie in moviesInDataBase) {
            if (isNotSameTitle(latestDateFound, movie)) {
                moviesForRecyclerView +=
                    WatchHistoryRecyclerItem.TitleRecyclerItem(composeTitle(movie.dateWatched))
                latestDateFound = movie.dateWatched
            }
            moviesForRecyclerView += WatchHistoryRecyclerItem.CardRecyclerItem(movie)
        }

        return moviesForRecyclerView
    }

    private fun isNotSameTitle(
        latestDateFound: Date?,
        movie: MovieUiState
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
    }
}