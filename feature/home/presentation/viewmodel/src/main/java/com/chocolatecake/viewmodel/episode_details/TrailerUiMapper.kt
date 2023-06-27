package com.chocolatecake.viewmodel.episode_details

import com.chocolatecake.entities.YoutubeVideoDetailsEntity
import com.chocolatecake.mapper.Mapper
import javax.inject.Inject

class TrailerUiMapper @Inject constructor() : Mapper<YoutubeVideoDetailsEntity, TrailerUiState> {
    override fun map(input: YoutubeVideoDetailsEntity): TrailerUiState {
        return TrailerUiState(
            videoKey = input.key
        )
    }
}