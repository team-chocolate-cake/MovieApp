package com.chocolatecake.ui.movie_details.adapter

import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.common.model.PeopleUIState
import com.chocolatecake.viewmodel.movieDetails.ReviewUiState
import com.chocolatecake.viewmodel.movieDetails.UpperUiState

sealed class MovieDetailsItem(val type: MovieDetailsType) {
    data class Upper(val upperUiState: UpperUiState) : MovieDetailsItem(MovieDetailsType.UPPER)
    data class People(val list: List<PeopleUIState>) : MovieDetailsItem(MovieDetailsType.PEOPLE)

    data class Recommended(val list: List<MediaVerticalUIState> ,
                           val isEmptyReviews:Boolean ,
                           val movieInt: Int,
                           val totalReviews:Int,
                           val totalPages: Boolean
    ) : MovieDetailsItem(MovieDetailsType.RECOMMENDED)

    data class Reviews(val list: ReviewUiState) : MovieDetailsItem(MovieDetailsType.REVIEWS)
}

enum class MovieDetailsType {UPPER, PEOPLE, RECOMMENDED, REVIEWS }