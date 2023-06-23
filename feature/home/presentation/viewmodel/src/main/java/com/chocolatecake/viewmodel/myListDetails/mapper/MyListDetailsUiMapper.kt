package com.chocolatecake.viewmodel.myListDetails.mapper

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.myListDetails.MovieUiState
import javax.inject.Inject

class MyListDetailsUiMapper @Inject constructor() : Mapper<MovieEntity, MovieUiState> {
    override fun map(input: MovieEntity): MovieUiState {
        return MovieUiState(
            id= input.id,
            title = input.title,
            imageUrl =input.imageUrl,
            genres = input.convertGenreListToString(),
            year = input.extractYearFromDate(),
            rate = input.rate,
        )
    }

    private fun MovieEntity.convertGenreListToString(): String {
        return genreEntities.joinToString(" | ") { it.genreName }
    }

    private fun MovieEntity.extractYearFromDate(): String {
        val parts = year.split("-")
        return parts[0]
    }
}