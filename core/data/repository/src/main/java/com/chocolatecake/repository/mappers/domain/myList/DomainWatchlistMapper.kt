package com.chocolatecake.repository.mappers.domain.myList

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.local.database.dto.movie.WatchlistLocalDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainWatchlistMapper @Inject constructor()
    :Mapper<WatchlistLocalDto, MovieEntity> {
    override fun map(input: WatchlistLocalDto): MovieEntity {
        return MovieEntity(
            id = input.id ?: 0,
            title = input.title ?:"",
            rate = input.rate ?:0.0,
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.imageUrl,
            year = input.year ?: "",
            genreEntities = emptyList(),
        )
    }
}