package com.chocolatecake.repository.mappers.domain.my_rated

import com.chocolatecake.entities.my_rated.MyRatedEpisodesEntity
import com.chocolatecake.remote.response.dto.episode_details.MyRatedEpisodesDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class MyRatedEpisodesDomainMapper @Inject constructor() :
    Mapper<MyRatedEpisodesDto, MyRatedEpisodesEntity> {

    override fun map(input: MyRatedEpisodesDto): MyRatedEpisodesEntity {
        return MyRatedEpisodesEntity(
            id = input.id ?: 0,
            name = input.name ?: "",
            episodeNumber = input.episodeNumber ?: 0,
            seasonNumber = input.seasonNumber ?: 0,
            showId = input.showId ?: 0,
            rating = input.rating ?: 0,
            voteAverage = input.voteAverage ?: 0,
            voteCount = input.voteCount ?: 0,
        )
    }
}