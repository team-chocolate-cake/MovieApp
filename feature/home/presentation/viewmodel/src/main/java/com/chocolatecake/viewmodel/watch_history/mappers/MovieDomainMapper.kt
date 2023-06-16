package com.chocolatecake.viewmodel.watch_history.mappers

import com.chocolatecake.entities.MovieInWatchHistoryEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.watch_history.state_managment.MovieUiState
import java.util.Date
import javax.inject.Inject

class MovieDomainMapper @Inject constructor() : Mapper<MovieUiState, MovieInWatchHistoryEntity> {
    override fun map(input: MovieUiState): MovieInWatchHistoryEntity {
        return MovieInWatchHistoryEntity(
            id = input.id ,
            title = input.title,
            description = input.description,
            voteAverage = input.rating,
            posterPath = input.imageUrl,
            dateWatched = Date(),
            year = input.year
        )
    }
}