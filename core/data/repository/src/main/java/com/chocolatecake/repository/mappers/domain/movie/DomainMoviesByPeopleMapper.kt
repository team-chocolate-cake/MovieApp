package com.chocolatecake.repository.mappers.domain.movie

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.remote.response.CastItem
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainMoviesByPeopleMapper @Inject constructor() : Mapper<CastItem?,MovieEntity> {
    override fun map(input: CastItem?):MovieEntity {

        return MovieEntity(
            id = input?.id ?: 0, input?.title ?: "", input?.posterPath ?: "", rate = input?.voteAverage
                ?: 0.0
        )

    }
}