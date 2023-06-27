package com.chocolatecake.viewmodel.movieDetails

import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.common.model.PeopleUIState
import com.chocolatecake.viewmodel.common.model.UserListUi


data class MovieDetailsUiState(
    val id: Int = 0,
    val movieUiState: UpperUiState = UpperUiState(),
    val recommendedUiState: List<MediaVerticalUIState> = emptyList(),
    val castUiState: List<PeopleUIState> = emptyList(),
    val reviewUiState: List<ReviewUiState> = emptyList(),
    val reviewsDetails: ReviewDetailsUiState = ReviewDetailsUiState(),
    val onErrors: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val isLogined: Boolean = false,
    val userRating: Float = 0f,
    val userLists: List<UserListUi> = emptyList(),
    val userSelectedLists: List<Int> = emptyList()
){
    val isFailure: Boolean get() =
        onErrors.isNotEmpty()
}


data class UpperUiState(
    val id: Int = 0,
    val backdropPath: String = "",
    val genres: List<String> = emptyList(),
    val title: String = "",
    val overview: String = "",
    val voteAverage: Float = 0f,
    val videoKey: String = "",
    val isLogined: Boolean = false,
)


data class ReviewUiState(
    val name: String?,
    val avatar_path: String?,
    val content: String?,
    val created_at: String?,
)

data class ReviewDetailsUiState(
    val page: Int = 1,
    val totalPages: Int = 1,
    val totalReviews: Int = 1
)