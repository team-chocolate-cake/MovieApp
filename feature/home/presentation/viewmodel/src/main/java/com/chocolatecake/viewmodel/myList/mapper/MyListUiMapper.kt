package com.chocolatecake.viewmodel.myList.mapper

import com.chocolatecake.entities.myList.ListCreatedEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.myList.ListMovieUiState
import javax.inject.Inject

class MyListUiMapper @Inject constructor() : Mapper<ListCreatedEntity, ListMovieUiState> {
    override fun map(input: ListCreatedEntity): ListMovieUiState {
        return ListMovieUiState(
            id = input.id ?: 0,
            itemCount = input.itemCount,
            listType = input.listType,
            name = input.name,
            posterPath = input.posterPath,
        )
    }
}