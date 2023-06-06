package com.chocolatecake.movieapp.data.remote.service

import com.chocolatecake.movieapp.data.remote.response.GenreMovieDto
import com.chocolatecake.movieapp.data.remote.response.GenresWrapperResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenresService {

    @GET("genre/movie/list")
    suspend fun getListOfGenresForMovies(
        @Query("language") language: String = "en"
    ): Response<GenresWrapperResponse<GenreMovieDto>>

}