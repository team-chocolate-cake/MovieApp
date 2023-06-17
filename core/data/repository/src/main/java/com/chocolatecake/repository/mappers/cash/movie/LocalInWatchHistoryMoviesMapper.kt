package com.chocolatecake.repository.mappers.cash.movie

import com.chocolatecake.entities.MovieInWatchHistoryEntity
import com.chocolatecake.local.database.dto.movie.MovieInWatchHistoryLocalDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class LocalInWatchHistoryMoviesMapper @Inject constructor()
    :Mapper<MovieInWatchHistoryEntity,MovieInWatchHistoryLocalDto> {
    override fun map(input: MovieInWatchHistoryEntity): MovieInWatchHistoryLocalDto {
        return MovieInWatchHistoryLocalDto(
            id = input.id,
            posterPath = input.posterPath,
            title = input.title,
            voteAverage = input.voteAverage,
            description = input.description,
            dateWatched = input.dateWatched,
            year = input.year
        )
    }
}