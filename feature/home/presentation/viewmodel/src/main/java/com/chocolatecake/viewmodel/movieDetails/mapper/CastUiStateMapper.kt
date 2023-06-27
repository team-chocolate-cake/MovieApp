package com.chocolatecake.viewmodel.movieDetails.mapper

import com.chocolatecake.entities.movieDetails.CastEntity
import com.chocolatecake.entities.movieDetails.RecommendedMovieEntity
import com.chocolatecake.entities.movieDetails.ReviewEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.common.model.PeopleUIState
import com.chocolatecake.viewmodel.movieDetails.ReviewUiState
import javax.inject.Inject

class CastUiStateMapper@Inject constructor() :
    Mapper<CastEntity, PeopleUIState> {
    override fun map(input: CastEntity): PeopleUIState {
        return PeopleUIState(
            id = input.id,
            name = input.name,
            imageUrl = input.profilePath
        )
    }

}