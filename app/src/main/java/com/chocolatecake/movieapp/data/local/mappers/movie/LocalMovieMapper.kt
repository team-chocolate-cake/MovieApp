package com.chocolatecake.movieapp.data.local.mappers.movie

import com.chocolatecake.movieapp.data.local.database.entity.movie.MovieEntity
import com.chocolatecake.movieapp.data.remote.response.MovieDto
import com.chocolatecake.movieapp.domain.mappers.Mapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LocalMovieMapper @Inject constructor(): Mapper<MovieDto, MovieEntity> {
    override fun map(input: MovieDto): MovieEntity {
        return MovieEntity(
            id = input.id ?: 0,
            voteAverage = input.voteAverage ?: 0.0,
            title = input.title,
            adult = input.adult,
            backdropPath = input.backdropPath,
            genreIds = input.genreIds,
            originalLanguage = input.originalLanguage,
            originalTitle = input.originalTitle,
            overview = input.overview,
            popularity = input.popularity,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            video = input.video,
            voteCount = input.voteCount,
        )
    }
}