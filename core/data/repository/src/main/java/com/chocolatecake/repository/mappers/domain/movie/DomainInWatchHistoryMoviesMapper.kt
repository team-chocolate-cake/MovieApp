package com.chocolatecake.repository.mappers.domain.movie

import com.chocolatecake.entities.MovieInWatchHistoryEntity
import com.chocolatecake.local.database.dto.movie.MovieInWatchHistoryLocalDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainInWatchHistoryMoviesMapper @Inject constructor()
    :Mapper<MovieInWatchHistoryLocalDto,MovieInWatchHistoryEntity>{
    override fun map(input: MovieInWatchHistoryLocalDto): MovieInWatchHistoryEntity {
        return MovieInWatchHistoryEntity(
            id = input.id,
            title = input.title,
            description = input.title,
            voteAverage = input.voteAverage,
            dateWatched = input.dateWatched,
            posterPath = input.posterPath,
            year = input.year
        )
    }
}