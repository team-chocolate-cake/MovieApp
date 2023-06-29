package com.chocolatecake.viewmodel.episode_details

import com.chocolatecake.entities.YoutubeVideoDetailsEntity
import com.chocolatecake.entities.my_rated.MyRatedEpisodesEntity
import com.chocolatecake.mapper.Mapper
import javax.inject.Inject

class UserRateUiMapper @Inject constructor() :
    Mapper<MyRatedEpisodesEntity, MyRatedEpisodesUiState> {
    override fun map(input: MyRatedEpisodesEntity): MyRatedEpisodesUiState {
        return MyRatedEpisodesUiState(
            rating = input.rating
        )
    }
}