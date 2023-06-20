package com.chocolatecake.viewmodel.tv_details.mappers

import com.chocolatecake.entities.YoutubeVideoDetailsEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiState

class TvShowYoutubeVideoDetailsUiMapper : Mapper<YoutubeVideoDetailsEntity, TvDetailsUiState> {
    override fun map(input: YoutubeVideoDetailsEntity): TvDetailsUiState {
        return TvDetailsUiState(youtubeKeyId = input.key)
    }
}