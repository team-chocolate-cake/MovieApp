package com.chocolatecake.movieapp.domain.mappers.search

import com.chocolatecake.movieapp.data.local.database.entity.movie.MovieEntity
import com.chocolatecake.movieapp.domain.mappers.Mapper
import com.chocolatecake.movieapp.domain.model.Movie
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MovieUIMapper @Inject constructor(): Mapper<MovieEntity,Movie> {
    override fun map(input: MovieEntity): Movie {
        return Movie(
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