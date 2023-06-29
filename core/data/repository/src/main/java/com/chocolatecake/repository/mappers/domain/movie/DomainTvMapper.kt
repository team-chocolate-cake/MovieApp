package com.chocolatecake.repository.mappers.domain.movie

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.remote.response.dto.TvRemoteDto
import com.chocolatecake.repository.BuildConfig
import javax.inject.Inject

class DomainTvMapper @Inject constructor() {
    fun map(input: TvRemoteDto, genres: List<GenreEntity> , mediaType:String="tv"): MovieEntity {
        return MovieEntity(
            id = input.id ?: 0,
            title = input.name ?: "",
            rate = input.voteAverage ?: 0.0,
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            year = input.firstAirDate ?: "",
            genreEntities = genres,
            mediaType = mediaType,
        )
    }
}