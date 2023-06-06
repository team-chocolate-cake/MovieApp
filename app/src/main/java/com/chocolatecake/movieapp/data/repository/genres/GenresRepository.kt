package com.chocolatecake.movieapp.data.repository.genres

import com.chocolatecake.movieapp.data.remote.response.GenreMovieDto

interface GenresRepository {
    suspend fun getGenresMovies(): List<GenreMovieDto>?
}