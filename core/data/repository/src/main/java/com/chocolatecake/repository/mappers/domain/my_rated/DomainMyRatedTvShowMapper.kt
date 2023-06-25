package com.chocolatecake.repository.mappers.domain.my_rated

import com.chocolatecake.entities.TVShowsEntity
import com.chocolatecake.entities.my_rated.MyRatedMovieEntity
import com.chocolatecake.entities.my_rated.MyRatedTvShowEntity
import com.chocolatecake.remote.response.dto.TVShowsRemoteDto
import com.chocolatecake.remote.response.dto.my_rated.MyRatedMovieDto
import com.chocolatecake.remote.response.dto.my_rated.MyRatedTvShowDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainMyRatedTvShowMapper @Inject constructor() :
    Mapper<MyRatedTvShowDto, MyRatedTvShowEntity> {

    override fun map(input: MyRatedTvShowDto): MyRatedTvShowEntity {
        return MyRatedTvShowEntity(
            id = input.id ?: 0,
            title = input.name ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath ,
            genreEntities = input.genreIds?:emptyList(),
            rate = input.rating ?: 0.0,
            year = input.firstAirDate?:""
        )
    }
}