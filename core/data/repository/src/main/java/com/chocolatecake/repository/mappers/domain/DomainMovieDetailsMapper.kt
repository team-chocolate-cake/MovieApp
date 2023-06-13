package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.movieDetails.CastEntity
import com.chocolatecake.entities.movieDetails.CreditsEntity
import com.chocolatecake.entities.movieDetails.CrewEntity
import com.chocolatecake.entities.movieDetails.MovieDetailsEntity
import com.chocolatecake.entities.movieDetails.MovieVideoEntity
import com.chocolatecake.entities.movieDetails.ProductionCompanyEntity
import com.chocolatecake.entities.movieDetails.ProductionCountryEntity
import com.chocolatecake.entities.movieDetails.RecommendationsEntity
import com.chocolatecake.entities.movieDetails.RecommendedMovieEntity
import com.chocolatecake.entities.movieDetails.SpokenLanguageEntity
import com.chocolatecake.entities.movieDetails.VideosEntity
import com.chocolatecake.remote.response.movieDetails.Credits
import com.chocolatecake.remote.response.movieDetails.MovieDetailsDto
import com.chocolatecake.remote.response.movieDetails.Recommendations
import com.chocolatecake.remote.response.movieDetails.Videos
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainMovieDetailsMapper @Inject constructor() : Mapper<MovieDetailsDto, MovieDetailsEntity> {
    override fun map(input: MovieDetailsDto): MovieDetailsEntity {
        return MovieDetailsEntity(
            adult = input.adult,
            backdropPath = input.backdropPath,
            belongsToCollection = input.belongsToCollection,
            budget = input.budget,
            credits = mapCredits(input.credits),
            genres = input.genres.map { it.name },
            homepage = input.homepage,
            id = input.id,
            imdbId = input.imdbId,
            originalLanguage = input.originalLanguage,
            originalTitle = input.originalTitle,
            overview = input.overview,
            popularity = input.popularity,
            posterPath = input.posterPath,
            productionCompanies = input.productionCompanies.map {
                ProductionCompanyEntity(
                    id = it.id,
                    name = it.name,
                    logoPath = it.logoPath,
                    originCountry = it.originCountry
                )
            },
            productionCountries = input.productionCountries.map {
                ProductionCountryEntity(
                    iso31661 = it.iso31661,
                    name = it.name
                )
            },
            recommendations = mapRecommendations(input.recommendations),
            releaseDate = input.releaseDate,
            revenue = input.revenue,
            runtime = input.runtime,
            spokenLanguages = input.spokenLanguages.map {
                SpokenLanguageEntity(
                    englishName = it.englishName,
                    iso6391 = it.iso6391,
                    name = it.name
                )
            },
            status = input.status,
            tagline = input.tagline,
            title = input.title,
            video = input.video,
            videos = mapvideos(input.videos),
            voteAverage = input.voteAverage,
            voteCount = input.voteCount
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
                    profilePath = it.profilePath
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
                    backdropPath = it.backdropPath,
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
}