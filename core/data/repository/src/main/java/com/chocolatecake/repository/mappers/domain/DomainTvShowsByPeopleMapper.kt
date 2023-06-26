package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.TVShowsEntity
import com.chocolatecake.entities.TvEntity
import com.chocolatecake.entities.TvShowEntity
import com.chocolatecake.remote.response.dto.TvShowsCastItem
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainTvShowsByPeopleMapper  @Inject constructor(): Mapper<TvShowsCastItem?, TvShowEntity> {
    override fun map(input: TvShowsCastItem?): TvShowEntity{

        return TvShowEntity(
            input?.id ?: 0, input?.name ?: "",
            (BuildConfig.IMAGE_BASE_PATH + input?.posterPath) ?: "", rate = (input?.voteAverage
                    ?: 0.0) as Double
            )


    }
}
