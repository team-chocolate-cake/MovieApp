package com.chocolatecake.repository.mappers.domain.movie

import com.chocolatecake.remote.response.TvShowsCastItem
import com.chocolatecake.entities.TvShowsEntity
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainTvShowsByPeopleMapper  @Inject constructor(): Mapper<TvShowsCastItem?, TvShowsEntity> {
    override fun map(input: TvShowsCastItem?): TvShowsEntity{

        return TvShowsEntity(
            input?.id ?: 0, input?.name ?: "",
            (BuildConfig.IMAGE_BASE_PATH + input?.posterPath) ?: "", rate = (input?.voteAverage
                    ?: 0.0) as Double
            )


    }
}
