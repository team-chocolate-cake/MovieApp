package com.chocolatecake.viewmodel.my_rated.mappers

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.entities.my_rated.MyRatedMovieEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.MovieHorizontalUIState
import javax.inject.Inject

class MyRatedMovieToMovieHorizontalUiMapper @Inject constructor()  : Mapper<MyRatedMovieEntity, MovieHorizontalUIState> {
    override fun map(input: MyRatedMovieEntity): MovieHorizontalUIState {
        return MovieHorizontalUIState(
            id = input.id,
            rate = input.myRate,
            title = input.title,
            imageUrl = input.imageUrl,
            year = extractYearFromDate(input.year),
            genres = convertGenreListToString(input.genreEntities.map { it.genreName }),
        )
    }

    private fun convertGenreListToString(list: List<String>): String {
        return list.joinToString(" | ")
    }

    private fun extractYearFromDate(year: String): String {
        val parts = year.split("-")
        return parts[0]
    }
}