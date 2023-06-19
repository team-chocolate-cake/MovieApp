package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.movieDetails.CastEntity
import com.chocolatecake.entities.movieDetails.CreditsEntity
import com.chocolatecake.entities.movieDetails.CrewEntity
import com.chocolatecake.entities.movieDetails.MovieDetailsEntity
import com.chocolatecake.entities.movieDetails.MovieVideoEntity
import com.chocolatecake.entities.movieDetails.RecommendationsEntity
import com.chocolatecake.entities.movieDetails.RecommendedMovieEntity
import com.chocolatecake.entities.movieDetails.ReviewEntity
import com.chocolatecake.entities.movieDetails.VideosEntity
import com.chocolatecake.remote.response.movieDetails.Credits
import com.chocolatecake.remote.response.movieDetails.MovieDetailsDto
import com.chocolatecake.remote.response.movieDetails.Recommendations
import com.chocolatecake.remote.response.movieDetails.Reviews
import com.chocolatecake.remote.response.movieDetails.Videos
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainMovieDetailsMapper @Inject constructor() : Mapper<MovieDetailsDto, MovieDetailsEntity> {
    override fun map(input: MovieDetailsDto): MovieDetailsEntity {
        return MovieDetailsEntity(
            backdropPath = input.backdropPath,
            credits = mapCredits(input.credits),
            genres = input.genres.map { it.name },
            id = input.id,
            overview = input.overview,
            recommendations = mapRecommendations(input.recommendations),
            title = input.title,
            video = input.video,
            videos = mapvideos(input.videos),
            voteAverage = input.voteAverage,
            reviewEntities = mapReviews(input.reviews)
        )
    }

    private fun mapCredits(credits: Credits): CreditsEntity {
        return CreditsEntity(
            cast = credits.cast.map {
                CastEntity(
                    adult = it.adult,
                    castId = it.castId,
                    character = it.character,
                    creditId = it.creditId,
                    gender = it.gender,
                    id = it.id,
                    knownForDepartment = it.knownForDepartment,
                    name = it.name,
                    order = it.order,
                    originalName = it.originalName,
                    popularity = it.popularity,
                    profilePath = BuildConfig.IMAGE_BASE_PATH+it.profilePath
                )
            },
            crew = credits.crew.map {
                CrewEntity(
                    adult = it.adult,
                    creditId = it.creditId,
                    department = it.department,
                    gender = it.gender,
                    id = it.id,
                    knownForDepartment = it.knownForDepartment,
                    name = it.name,
                    job = it.job,
                    originalName = it.originalName,
                    popularity = it.popularity,
                    profilePath = it.profilePath
                )
            },
        )
    }

    private fun mapRecommendations(recommendations: Recommendations): RecommendationsEntity {
        return RecommendationsEntity(
            page = recommendations.page,
            recommendedMovies = recommendations.recommendedMovies.map {
                RecommendedMovieEntity(
                    adult = it.adult,
                    backdropPath = BuildConfig.IMAGE_BASE_PATH+it.backdropPath,
                    genreIds = it.genreIds,
                    id = it.id,
                    mediaType = it.mediaType,
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    title = it.title,
                    video = it.video,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount,
                )
            },
            totalPages = recommendations.totalPages,
            totalResults = recommendations.totalResults
        )
    }

    private fun mapvideos(videos: Videos): VideosEntity {
        return VideosEntity(
            results = videos.results.map {
                MovieVideoEntity(
                    id =it.id,
                    iso31661 =it.iso31661,
                    iso6391 =it.iso6391,
                    key =it.key,
                    name =it.name,
                    official =it.official,
                    publishedAt =it.publishedAt,
                    site =it.site,
                    size =it.size,
                    type =it.type
                )
            }
        )
    }

    private fun mapReviews(reviews: Reviews): List<ReviewEntity> {
        return reviews.results.map {
            ReviewEntity(
                name = it.author,
                avatar_path = it.authorDetails.avatarPath,
                content = it.content,
                created_at = it.createdAt
            )
        }
    }
}