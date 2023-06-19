package com.chocolatecake.viewmodel.search.mappers

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.MovieHorizontalUIState
import javax.inject.Inject

class MovieUiMapper @Inject constructor()  : Mapper<MovieEntity, MovieHorizontalUIState> {
    override fun map(input: MovieEntity): MovieHorizontalUIState {
        return MovieHorizontalUIState(
            id = input.id,
            rate = input.rate,
            title = input.title,
            imageUrl = input.imageUrl,
            year = extractYearFromDate(input.year),
            genres = convertGenreListToString(input.genreEntities),
        )
    }

    private fun convertGenreListToString(list: List<GenreEntity>): String {
        return list.joinToString(" | ") { it.genreName }
    }

    private fun extractYearFromDate(year: String): String {
        val parts = year.split("-")
        return parts[0]
    }
}