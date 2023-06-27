package com.chocolatecake.viewmodel.movieDetails.mapper

import com.chocolatecake.entities.movieDetails.MovieDetailsEntity
import com.chocolatecake.entities.movieDetails.RecommendedMovieEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.movieDetails.UpperUiState
import javax.inject.Inject

class RecommendedUiStateMapper @Inject constructor() :
    Mapper<RecommendedMovieEntity, MediaVerticalUIState> {
    override fun map(input: RecommendedMovieEntity): MediaVerticalUIState {
        return MediaVerticalUIState(
            id = input.id,
            rate = input.voteAverage,
            imageUrl = input.posterPath,
        )
    }

}