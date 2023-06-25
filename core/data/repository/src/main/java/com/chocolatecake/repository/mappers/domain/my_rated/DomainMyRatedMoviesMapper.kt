package com.chocolatecake.repository.mappers.domain.my_rated

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.my_rated.MyRatedMovieEntity
import com.chocolatecake.remote.response.dto.my_rated.MyRatedMovieDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainMyRatedMoviesMapper @Inject constructor(private val genreEntities: List<GenreEntity>) :
    Mapper<MyRatedMovieDto, MyRatedMovieEntity> {

    override fun map(input: MyRatedMovieDto): MyRatedMovieEntity {
        return MyRatedMovieEntity(
            id = input.id ?: 0,
            title = input.title ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath ,
            genreEntities = genreEntities.filter {
                it.genreID in (input.genreIds?.filterNotNull() ?: emptyList())
            },
            myRate = input.rating ?: 0.0,
            year = input.releaseDate?:""
        )
    }
}