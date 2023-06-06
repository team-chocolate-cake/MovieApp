package com.chocolatecake.movieapp.data.local.mappers.movie

import com.chocolatecake.movieapp.BuildConfig
import com.chocolatecake.movieapp.data.local.database.entity.movie.TrendingMoviesEntity
import com.chocolatecake.movieapp.data.remote.response.MovieDto
import com.chocolatecake.movieapp.domain.mappers.Mapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LocalTrendingMoviesMapper @Inject constructor() : Mapper<MovieDto, TrendingMoviesEntity> {
    override fun map(input: MovieDto): TrendingMoviesEntity {
        return TrendingMoviesEntity(
            id = input.id ?: 0,
            imageUrl = input.posterPath.takeIf {
                it!=null
            }?.let { (BuildConfig.IMAGE_BASE_PATH + it) }?: "https://www.themoviedb.org/assets/2/apple-touch-icon-cfba7699efe7a742de25c28e08c38525f19381d31087c69e89d6bcb8e3c0ddfa.png",
            rate = input.voteAverage ?: 0.0
        )
    }
}