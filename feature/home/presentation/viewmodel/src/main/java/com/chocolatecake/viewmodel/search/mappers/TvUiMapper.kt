package com.chocolatecake.viewmodel.search.mappers

import com.chocolatecake.entities.TvEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.MovieHorizontalUIState
import javax.inject.Inject

class TvUiMapper @Inject constructor()  : Mapper<TvEntity, MovieHorizontalUIState> {
    override fun map(input: TvEntity): MovieHorizontalUIState {
        return MovieHorizontalUIState(
            id = input.id,
            rate = input.rate,
            title = input.name,
            imageUrl = input.imageUrl,
            year = input.extractYearFromDate(),
            genres = input.convertGenreListToString()
        )
    }
}