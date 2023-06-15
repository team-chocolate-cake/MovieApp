package com.chocolatecake.viewmodel.people.mapper

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.people.PeopleDetailsUiState
import javax.inject.Inject

class MoviesByPeopleUiMapper @Inject constructor():  Mapper<MovieEntity, MediaVerticalUIState> {
    override fun map(input: MovieEntity): MediaVerticalUIState {
        return MediaVerticalUIState(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}