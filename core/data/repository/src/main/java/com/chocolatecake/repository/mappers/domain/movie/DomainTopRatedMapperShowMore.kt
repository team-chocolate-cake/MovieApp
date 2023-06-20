package com.chocolatecake.repository.mappers.domain.movie

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.remote.response.dto.MovieRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainTopRatedMapperShowMore@Inject constructor():
    Mapper<MovieRemoteDto, MovieEntity> {

    override fun map(input: MovieRemoteDto): MovieEntity {
        return MovieEntity(
            id = input.id?:0,
            title = input.title?:"",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            year=input.releaseDate?:"",
            genreEntities = emptyList(),
            rate = input.voteAverage?:0.0
        )
    }
}