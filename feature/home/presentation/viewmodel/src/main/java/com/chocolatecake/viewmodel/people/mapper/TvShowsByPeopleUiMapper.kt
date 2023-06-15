package com.chocolatecake.viewmodel.people.mapper

import com.chocolatecake.entities.TvShowsEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.people.PeopleDetailsUiState
import javax.inject.Inject

class TvShowsByPeopleUiMapper @Inject constructor() :  Mapper<TvShowsEntity, MediaVerticalUIState> {
    override fun map(input: TvShowsEntity): MediaVerticalUIState {
        return MediaVerticalUIState(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}