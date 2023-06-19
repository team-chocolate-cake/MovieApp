package com.chocolatecake.remote.service

import com.chocolatecake.remote.request.LoginRequest
import com.chocolatecake.remote.request.RatingRequest
import com.chocolatecake.remote.response.DataWrapperResponse
import com.chocolatecake.remote.response.GenresWrapperResponse
import com.chocolatecake.remote.response.auth.RequestTokenResponse
import com.chocolatecake.remote.response.auth.SessionResponse
import com.chocolatecake.remote.response.dto.GenreMovieRemoteDto
import com.chocolatecake.remote.response.dto.GenreTvRemoteDto
import com.chocolatecake.remote.response.dto.MovieRemoteDto
import com.chocolatecake.remote.response.dto.PeopleRemoteDto
import com.chocolatecake.remote.response.dto.TVShowsRemoteDto
import com.chocolatecake.remote.response.dto.TvRemoteDto
import com.chocolatecake.remote.response.dto.profile.ProfileRemoteDto
import com.chocolatecake.remote.response.movieDetails.MovieDetailsDto
import com.chocolatecake.remote.response.movieDetails.RatingDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
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
    suspend fun getTopRatedMovies(@Query("page") page: Int = 1): Response<DataWrapperResponse<MovieRemoteDto>>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int = 1): Response<DataWrapperResponse<MovieRemoteDto>>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int = 1): Response<DataWrapperResponse<MovieRemoteDto>>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int = 1): Response<DataWrapperResponse<MovieRemoteDto>>

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendedMovies(
        @Query("page") page: Int = 1,
        @Path("movie_id") movieId: Int
    ): Response<DataWrapperResponse<MovieRemoteDto>>

    @GET("movie/latest")
    suspend fun getLatestMovie(): Response<MovieRemoteDto>

    @GET("trending/movie/{time_window}")
    suspend fun getTrendingMovies(
        @Path("time_window") timeWindow: String = "day"
    ): Response<DataWrapperResponse<MovieRemoteDto>>
    ///endregion

    /// region tv

    @GET("tv/airing_today")
    suspend fun getAiringTodayTVShows(@Query("page") page: Int = 1): Response<DataWrapperResponse<TVShowsRemoteDto>>

    @GET("tv/top_rated")
    suspend fun getTopRatedTVShows(@Query("page") page: Int = 1): Response<DataWrapperResponse<TVShowsRemoteDto>>

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTVShows(@Query("page") page: Int = 1): Response<DataWrapperResponse<TVShowsRemoteDto>>

    @GET("tv/popular")
    suspend fun getPopularTVShows(@Query("page") page: Int = 1): Response<DataWrapperResponse<TVShowsRemoteDto>>

//    @GET("genre/tv/list")
//    suspend fun getListOfGenresForTvs(
//        @Query("page") page: Int = 1,
//    ): Response<GenresWrapperResponse<GenreTvRemoteDto>>

    /// endregion

    /// region search
    @GET("search/movie")
     suspend fun searchForMovies(
        @Query("query") query: String,
        @Query("year") year: Int? = null,
        @Query("primary_release_year") primaryReleaseYear: Int? = null,
        @Query("region") region: String? = null,
        @Query("page") page: Int = 1,
    ): Response<DataWrapperResponse<MovieRemoteDto>>

    @GET("search/tv")
    suspend fun searchForTv(
        @Query("query") query: String,
        @Query("year") year: Int? = null,
        @Query("first_air_date_year") firstAirDateYear: String? = null,
        @Query("region") region: String? = null,
        @Query("page") page: Int = 1,
    ): Response<DataWrapperResponse<TvRemoteDto>>

    @GET("search/person")
    suspend fun searchForPeople(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
    ): Response<DataWrapperResponse<PeopleRemoteDto>>

    /// endregion

    /// region popular people
    @GET("person/popular")
    suspend fun getPopularPeople(@Query("page") page: Int = 1): Response<DataWrapperResponse<PeopleRemoteDto>>
    /// endregion

    /// region genres
    @GET("genre/movie/list")
    suspend fun getListOfGenresForMovies(
        @Query("language") language: String = "en"
    ): Response<GenresWrapperResponse<GenreMovieRemoteDto>>

    @GET("genre/tv/list")
    suspend fun getListOfGenresForTvs(
        @Query("language") language: String = "en"
    ): Response<GenresWrapperResponse<GenreTvRemoteDto>>
    ///endregion

    /// region account
    @GET("account")
    suspend fun getAccountDetails(
        @Query("session_id") sessionId : String = " "
    ) : Response<ProfileRemoteDto>
    ///endregion

    @GET("movie/{movieId}?&append_to_response=videos,credits,recommendations,reviews")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int
    ): Response<MovieDetailsDto>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("movie/{movieId}/rating")
    suspend fun setMovieRate(
        @Body ratingRequest: RatingRequest,
        @Path("movieId") movieId: Int
    ):Response<RatingDto>
}