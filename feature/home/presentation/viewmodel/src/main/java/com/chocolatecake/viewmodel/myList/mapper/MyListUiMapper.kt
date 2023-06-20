package com.chocolatecake.viewmodel.myList.mapper
import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.entities.myList.ListMovieEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.myList.ListMovieUiState
import com.chocolatecake.viewmodel.myListDetails.MovieUiState
import javax.inject.Inject

class MyListUiMapper @Inject constructor() : Mapper<ListMovieEntity, ListMovieUiState> {
    override fun map(input: ListMovieEntity): ListMovieUiState {
        return ListMovieUiState(
            mediaId = input.mediaId ?: 0,
            mediaType = input.mediaType ?: "",
            posterPath = input.posterPath ?: "",
            listId = input.listId ?: 0,
            nameList = input.nameList ?:"",
        )
    }
}