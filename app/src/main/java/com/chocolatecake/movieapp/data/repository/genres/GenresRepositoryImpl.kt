package com.chocolatecake.movieapp.data.repository.genres

import com.chocolatecake.movieapp.data.remote.response.GenreMovieDto
import com.chocolatecake.movieapp.data.remote.service.MovieService
import com.chocolatecake.movieapp.data.repository.base.BaseRepository
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(
    private val service: MovieService,
) : BaseRepository(), GenresRepository {
    override suspend fun getGenresMovies(): List<GenreMovieDto>? {
        return service.getListOfGenresForMovies().body()?.results
    }
}