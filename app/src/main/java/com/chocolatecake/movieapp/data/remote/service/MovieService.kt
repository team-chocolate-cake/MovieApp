package com.chocolatecake.movieapp.data.remote.service

import com.chocolatecake.movieapp.data.remote.request.LoginRequest
import com.chocolatecake.movieapp.data.remote.response.DataWrapperResponse
import com.chocolatecake.movieapp.data.remote.response.MovieDto
import com.chocolatecake.movieapp.data.remote.response.auth.RequestTokenResponse
import com.chocolatecake.movieapp.data.remote.response.auth.SessionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MovieService {

    /// region auth
    @FormUrlEncoded
    @POST("authentication/session/new")
    suspend fun createSession(@Field("request_token") requestToken: String): Response<SessionResponse>

    @GET("authentication/token/new")
    suspend fun createRequestToken(): Response<RequestTokenResponse>

    @POST("authentication/token/validate_with_login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<RequestTokenResponse>
    /// endregion

    /// region movie
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int = 1): Response<DataWrapperResponse<MovieDto>>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int = 1): Response<DataWrapperResponse<MovieDto>>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int = 1): Response<DataWrapperResponse<MovieDto>>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int = 1): Response<DataWrapperResponse<MovieDto>>

    @GET("movie/latest")
    suspend fun getLatestMovie(): Response<MovieDto>
    /// endregion

    /// region search
    @GET("search/movie")
     fun getSearchMovies(
        @Query("query") query: String,
        @Query("year") year: Int? = null,
        @Query("primary_release_year") primaryReleaseYear: Int? = null,
        @Query("region") region: String? = null,
        @Query("page") page: Int = 1,
    ): Response<DataWrapperResponse<MovieDto>>

    /// endregion
}