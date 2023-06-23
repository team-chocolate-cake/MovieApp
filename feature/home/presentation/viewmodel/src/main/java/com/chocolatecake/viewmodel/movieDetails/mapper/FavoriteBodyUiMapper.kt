package com.chocolatecake.viewmodel.movieDetails.mapper

import com.chocolatecake.entities.myList.FavoriteBodyRequestEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.movieDetails.FavoriteBodyUiState
import javax.inject.Inject

class FavoriteBodyUiMapper @Inject constructor() :
    Mapper<FavoriteBodyRequestEntity, FavoriteBodyUiState> {
    override fun map(input: FavoriteBodyRequestEntity): FavoriteBodyUiState {
        return FavoriteBodyUiState(
            favorite = input.favorite,
            mediaId = input.mediaId,
            mediaType = input.mediaType,
        )
    }

}