package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.remote.response.dto.CastItem
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainMoviesByPeopleMapper @Inject constructor() : Mapper<CastItem?, MovieEntity> {
    override fun map(input: CastItem?): MovieEntity {

        return MovieEntity(
            id = input?.id ?: 0,
            input?.title ?: "",
            (BuildConfig.IMAGE_BASE_PATH + input?.posterPath),
            genreEntities = emptyList(),
            rate = input?.voteAverage
                ?: 0.0
        )

    }
}