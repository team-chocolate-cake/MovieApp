package com.chocolatecake.movieapp.data.remote.service

import com.chocolatecake.movieapp.data.remote.request.LoginRequest
import com.chocolatecake.movieapp.data.remote.response.DataWrapperResponse
import com.chocolatecake.movieapp.data.remote.response.GenreMovieDto
import com.chocolatecake.movieapp.data.remote.response.GenresWrapperResponse
import com.chocolatecake.movieapp.data.remote.response.MovieDto
import com.chocolatecake.movieapp.data.remote.response.TvDto
import com.chocolatecake.movieapp.data.remote.response.actor.ActorDto
import com.chocolatecake.movieapp.data.remote.response.auth.RequestTokenResponse
import com.chocolatecake.movieapp.data.remote.response.auth.SessionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
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

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendedMovies(
        @Query("page") page: Int = 1,
        @Path("movie_id") movieId: Int
    ): Response<DataWrapperResponse<MovieDto>>

    @GET("movie/latest")
    suspend fun getLatestMovie(): Response<MovieDto>

    @GET("trending/movie/{time_window}")
    suspend fun getTrendingMovies(
        @Path("time_window") timeWindow: String = "day"
    ): Response<DataWrapperResponse<MovieDto>>
    ///endregion

    /// region search
    @GET("search/movie")
     suspend fun searchForMovies(
        @Query("query") query: String,
        @Query("year") year: Int? = null,
        @Query("primary_release_year") primaryReleaseYear: Int? = null,
        @Query("region") region: String? = null,
        @Query("page") page: Int = 1,
    ): Response<DataWrapperResponse<MovieDto>>

     @GET("search/tv")
     suspend fun searchForTv(
         @Query("query") query: String,
         @Query("year") year: Int? = null,
         @Query("first_air_date_year") firstAirDateYear: String? = null,
         @Query("region") region: String? = null,
         @Query("page") page: Int = 1,
     ): Response<DataWrapperResponse<TvDto>>

    /// endregion

    /// region popular people
    @GET("person/popular")
    suspend fun getPopularPeople(@Query("page") page: Int = 1): Response<DataWrapperResponse<ActorDto>>
    /// endregion

    /// region genres
    @GET("genre/movie/list")
    suspend fun getListOfGenresForMovies(
        @Query("language") language: String = "en"
    ): Response<GenresWrapperResponse<GenreMovieDto>>
    ///endregion
}