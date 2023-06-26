package com.chocolatecake.viewmodel.movieDetails.mapper

import com.chocolatecake.entities.movieDetails.RecommendedMovieEntity
import com.chocolatecake.entities.movieDetails.ReviewEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.movieDetails.ReviewUiState
import javax.inject.Inject

class ReviewsUiStateMapper@Inject constructor() :
    Mapper<ReviewEntity, ReviewUiState> {
    override fun map(input: ReviewEntity): ReviewUiState {
        return ReviewUiState(
            name = input.name,
            avatar_path = input.avatar_path,
            content = input.content,
            created_at = input.created_at
        )
    }

}