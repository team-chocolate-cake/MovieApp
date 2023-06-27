package com.chocolatecake.viewmodel.movieDetails.mapper

import com.chocolatecake.entities.movieDetails.CastEntity
import com.chocolatecake.entities.movieDetails.MovieDetailsEntity
import com.chocolatecake.entities.movieDetails.RecommendedMovieEntity
import com.chocolatecake.entities.movieDetails.ReviewEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.common.model.PeopleUIState
import com.chocolatecake.viewmodel.movieDetails.ReviewDetailsUiState
import com.chocolatecake.viewmodel.movieDetails.ReviewUiState
import javax.inject.Inject

class ReviewDetailsUiStateMapper@Inject constructor() :
    Mapper<MovieDetailsEntity, ReviewDetailsUiState> {
    override fun map(input: MovieDetailsEntity): ReviewDetailsUiState {
        return ReviewDetailsUiState(
            page = input.reviewEntity.page,
            totalPages = input.reviewEntity.totalPages,
            totalReviews = input.reviewEntity.totalResults
        )
    }

}