package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.movieDetails.CastEntity
import com.chocolatecake.entities.movieDetails.CreditsEntity
import com.chocolatecake.entities.movieDetails.CrewEntity
import com.chocolatecake.entities.movieDetails.MovieDetailsEntity
import com.chocolatecake.entities.movieDetails.MovieVideoEntity
import com.chocolatecake.entities.movieDetails.RecommendationsEntity
import com.chocolatecake.entities.movieDetails.RecommendedMovieEntity
import com.chocolatecake.entities.movieDetails.ReviewEntity
import com.chocolatecake.entities.movieDetails.ReviewResponseEntity
import com.chocolatecake.entities.movieDetails.VideosEntity
import com.chocolatecake.remote.response.movieDetails.Credits
import com.chocolatecake.remote.response.movieDetails.MovieDetailsDto
import com.chocolatecake.remote.response.movieDetails.Recommendations
import com.chocolatecake.remote.response.movieDetails.ReviewsDto
import com.chocolatecake.remote.response.movieDetails.Videos
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainMovieDetailsMapper @Inject constructor() : Mapper<MovieDetailsDto, MovieDetailsEntity> {
    override fun map(input: MovieDetailsDto): MovieDetailsEntity {
        return MovieDetailsEntity(
            backdropPath = input.backdropPath ?:"",
            credits = mapCredits(input.credits),
            genres = input.genres?.map { it.name!! }?: emptyList(),
            id = input.id?:0,
            overview = input.overview?:"",
            recommendations = mapRecommendations(input.recommendations),
            title = input.title?:"",
            video = input.video?:false,
            videos = mapvideos(input.videos),
            voteAverage = input.voteAverage?:0.0,
            reviewEntity = mapReviews(input.reviews)
        )
    }

    private fun mapCredits(credits: Credits?): CreditsEntity {
        return CreditsEntity(
            cast = credits?.cast?.map {
                CastEntity(
                    adult = it.adult?:false,
                    castId = it.castId?:0,
                    character = it.character?:"",
                    creditId = it.creditId?:"",
                    gender = it.gender?:0,
                    id = it.id?:0,
                    knownForDepartment = it.knownForDepartment?:"",
                    name = it.name?:"",
                    order = it.order?:0,
                    originalName = it.originalName?:"",
                    popularity = it.popularity?:0.0,
                    profilePath = BuildConfig.IMAGE_BASE_PATH+it.profilePath
                )
            }?: emptyList(),
            crew = credits?.crew?.map {
                CrewEntity(
                    adult = it.adult?:false,
                    creditId = it.creditId?:"",
                    department = it.department?:"",
                    gender = it.gender?:0,
                    id = it.id?:0,
                    knownForDepartment = it.knownForDepartment?:"",
                    name = it.name?:"",
                    job = it.job?:"",
                    originalName = it.originalName?:"",
                    popularity = it.popularity?:0.0,
                    profilePath = it.profilePath?:""
                )
            }?: emptyList(),
        )
    }

    private fun mapRecommendations(recommendations: Recommendations?): RecommendationsEntity {
        return RecommendationsEntity(
            page = recommendations?.page?:0,
            recommendedMovies = recommendations?.recommendedMovies?.map {
                RecommendedMovieEntity(
                    adult = it.adult?:false,
                    backdropPath = BuildConfig.IMAGE_BASE_PATH+it.backdropPath,
                    genreIds = it.genreIds?: emptyList(),
                    id = it.id?:0,
                    mediaType = it.mediaType?:"",
                    originalLanguage = it.originalLanguage?:"",
                    originalTitle = it.originalTitle?:"",
                    overview = it.overview?:"",
                    popularity = it.popularity?:0.0,
                    posterPath = it.posterPath?:"",
                    releaseDate = it.releaseDate?:"",
                    title = it.title?:"",
                    video = it.video?:false,
                    voteAverage = it.voteAverage?:0.0,
                    voteCount = it.voteCount?:0,
                )
            }?: emptyList(),
            totalPages = recommendations?.totalPages?:0,
            totalResults = recommendations?.totalResults?:0
        )
    }

    private fun mapvideos(videos: Videos?): VideosEntity {
        return VideosEntity(
            results = videos?.results?.map {
                MovieVideoEntity(
                    id =it.id?:"",
                    iso31661 =it.iso31661?:"",
                    iso6391 =it.iso6391?:"",
                    key =it.key?:"",
                    name =it.name?:"",
                    official =it.official?:false,
                    publishedAt =it.publishedAt?:"",
                    site =it.site?:"",
                    size =it.size?:0,
                    type =it.type?:""
                )
            }?: emptyList()
        )
    }

    private fun mapReviews(reviews: ReviewsDto?): ReviewResponseEntity{
        return ReviewResponseEntity(
            reviews = reviews?.results?.map {
                ReviewEntity(
                    name = it.author?:"",
                    avatar_path = it.authorDetails?.avatarPath?:"",
                    content = it.content?:"",
                    created_at = it.createdAt?:""
                )
            }?: emptyList(),
            page = reviews?.page?:0,
            totalResults = reviews?.totalResults?:0,
            totalPages = reviews?.totalPages?:0

        )
    }
}