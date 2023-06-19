package com.chocolatecake.viewmodel.movieDetails

import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.common.model.PeopleUIState

sealed class MovieDetailsItem(val type: MovieDetailsType) {
    data class Upper(val upperUiState: UpperUiState?) : MovieDetailsItem(MovieDetailsType.UPPER)
    data class People(val list: List<PeopleUIState>?) : MovieDetailsItem(MovieDetailsType.PEOPLE)

    data class Recommended(val list: List<MediaVerticalUIState>?) : MovieDetailsItem(MovieDetailsType.RECOMMENDED)

    data class Reviews(val list: List<ReviewUiState>?) : MovieDetailsItem(MovieDetailsType.REVIEWS)
}

enum class MovieDetailsType {UPPER, PEOPLE, RECOMMENDED, REVIEWS }