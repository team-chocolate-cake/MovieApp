package com.chocolatecake.repository.mappers.domain.my_rated

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.my_rated.MyRatedTvShowEntity
import com.chocolatecake.remote.response.dto.my_rated.MyRatedTvShowDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainMyRatedTvShowMapper @Inject constructor() {

    fun map(input: MyRatedTvShowDto, genreEntities: List<GenreEntity>): MyRatedTvShowEntity {
        return MyRatedTvShowEntity(
            id = input.id ?: 0,
            title = input.name ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            genreEntities = genreEntities.filter {
                it.genreID in (input.genreIds?.filterNotNull() ?: emptyList())
            },
            rate = input.rating ?: 0.0,
            year = input.firstAirDate ?: ""
        )
    }
}