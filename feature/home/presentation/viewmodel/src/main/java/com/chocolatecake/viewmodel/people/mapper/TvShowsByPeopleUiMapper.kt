package com.chocolatecake.viewmodel.people.mapper

import com.chocolatecake.entities.TvShowEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import javax.inject.Inject

class TvShowsByPeopleUiMapper @Inject constructor() :  Mapper<TvShowEntity, MediaVerticalUIState> {
    override fun map(input: TvShowEntity): MediaVerticalUIState {
        return MediaVerticalUIState(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}