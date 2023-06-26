package com.chocolatecake.repository.mappers.domain.my_rated

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.my_rated.MyRatedMovieEntity
import com.chocolatecake.remote.response.dto.my_rated.MyRatedMovieDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainMyRatedMoviesMapper @Inject constructor() {
    fun map(input: MyRatedMovieDto ,genreEntities: List<GenreEntity>): MyRatedMovieEntity {
        return MyRatedMovieEntity(
            id = input.id ?: 0,
            title = input.title ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            genreEntities = genreEntities.filter {
                it.genreID in (input.genreIds?.filterNotNull() ?: emptyList())
            },
            myRate = input.rating ?: 0.0,
            year = input.releaseDate ?: ""
        )
    }
}