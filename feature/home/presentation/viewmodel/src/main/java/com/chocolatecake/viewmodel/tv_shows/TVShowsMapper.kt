package com.chocolatecake.viewmodel.tv_shows

import com.chocolatecake.entities.TVShowsEntity
import com.chocolatecake.mapper.Mapper

import javax.inject.Inject

class TVShowsMapper @Inject constructor() :
    Mapper<TVShowsEntity?, TVShowsUI> {
    override fun map(input: TVShowsEntity?): TVShowsUI {
        return TVShowsUI(
            id = input?.id,
            imageUrl = input?.imageUrl,
            rate = input?.rate
        )
    }
}