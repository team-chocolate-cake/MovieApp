package com.chocolatecake.movieapp.domain.usecases.genres

import com.chocolatecake.movieapp.data.repository.genres.GenresRepository
import com.chocolatecake.movieapp.domain.mappers.genre.GenreMapper
import com.chocolatecake.movieapp.domain.model.Genre
import javax.inject.Inject

class GetAllGenresMoviesUseCase @Inject constructor(
    private val genresRepository: GenresRepository,
    private val genreMapper: GenreMapper
) {
    suspend operator fun invoke(): List<Genre>? {
        return genresRepository.getGenresMovies()?.map {
            genreMapper.map(it)
        }?.sortedBy { it.genreName }
    }
}