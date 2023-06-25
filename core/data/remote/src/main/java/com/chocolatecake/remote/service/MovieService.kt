package com.chocolatecake.remote.service

import com.chocolatecake.remote.request.AddMediaToListRequest
import com.chocolatecake.remote.request.CreateUserListRequest
import com.chocolatecake.remote.request.FavoriteRequest
import com.chocolatecake.remote.request.ListRequest
import com.chocolatecake.remote.request.LoginRequest
import com.chocolatecake.remote.request.RateRequest
import com.chocolatecake.remote.request.RatingEpisodeDetailsRequest
import com.chocolatecake.remote.request.RatingRequest
import com.chocolatecake.remote.request.WatchlistRequest
import com.chocolatecake.remote.response.DataWrapperResponse
import com.chocolatecake.remote.response.GenresWrapperResponse
import com.chocolatecake.remote.response.ListDetailsWrapperResponse
import com.chocolatecake.remote.response.ListResponse
import com.chocolatecake.remote.response.MovieResponse
import com.chocolatecake.remote.response.auth.RequestTokenResponse
import com.chocolatecake.remote.response.auth.SessionResponse
import com.chocolatecake.remote.response.dto.GenreMovieRemoteDto
import com.chocolatecake.remote.response.dto.GenreTVRemoteDto
import com.chocolatecake.remote.response.dto.ListRemoteDto
import com.chocolatecake.remote.response.dto.MovieItemListRemoteDto
import com.chocolatecake.remote.response.dto.MovieRemoteDto
import com.chocolatecake.remote.response.dto.PeopleRemoteDto
import com.chocolatecake.remote.response.dto.StatusResponse
import com.chocolatecake.remote.response.dto.TVShowsRemoteDto
import com.chocolatecake.remote.response.dto.TvDetailsCreditRemoteDto
import com.chocolatecake.remote.response.dto.TvDetailsRemoteDto
import com.chocolatecake.remote.response.dto.TvRemoteDto
import com.chocolatecake.remote.response.dto.TvReviewRemoteDto
import com.chocolatecake.remote.response.dto.UserListRemoteDto
import com.chocolatecake.remote.response.dto.YoutubeVideoDetailsRemoteDto
import com.chocolatecake.remote.response.dto.episode_details.EpisodeDetailsCastRemoteDto
import com.chocolatecake.remote.response.dto.episode_details.EpisodeDetailsRemoteDto
import com.chocolatecake.remote.response.dto.episode_details.RatingEpisodeDetailsRemoteDto
import com.chocolatecake.remote.response.dto.profile.ProfileRemoteDto
import com.chocolatecake.remote.response.dto.season_details.SeasonDetailsDto
import com.chocolatecake.remote.response.movieDetails.MovieDetailsDto
import com.chocolatecake.remote.response.movieDetails.ReviewsDto
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
        @Path("time_window") timeWindow: String = "day",
        @Query("page") page: Int = 1
    ): Response<DataWrapperResponse<MovieRemoteDto>>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieYoutubeVideoDetails(
        @Path("movie_id") tvShowId: Int
    ): Response<DataWrapperResponse<YoutubeVideoDetailsRemoteDto>>

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
    ): Response<GenresWrapperResponse<GenreTVRemoteDto>>
    ///endregion

    /// region account
    @GET("account")
    suspend fun getAccountDetails(
        @Query("session_id") sessionId: String = " "
    ): Response<ProfileRemoteDto>
    ///endregion

    /// region movie details
    @GET("movie/{movieId}?&append_to_response=videos,credits,recommendations,reviews")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int
    ): Response<MovieDetailsDto>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("movie/{movieId}/rating")
    suspend fun setMovieRate(
        @Body ratingRequest: RatingRequest,
        @Path("movieId") movieId: Int
    ):Response<StatusResponse>
    /// endregion

    /// region season details
    @GET("tv/{series_id}/season/{season_number}")
    suspend fun getSeasonDetails(
        @Path("series_id") series_id : Int,
        @Path("season_number") season_number : Int
    ): Response<SeasonDetailsDto>
    ///endregion

    /// region tv details
    @GET("tv/{tv_id}")
    suspend fun getTvDetails(
        @Path("tv_id") tvShowId: Int
    ): Response<TvDetailsRemoteDto>

    @GET("tv/{tv_id}/aggregate_credits")
    suspend fun getTvDetailsCredit(
        @Path("tv_id") tvShowId: Int
    ): Response<TvDetailsCreditRemoteDto>

    @POST("tv/{tv_id}/rating?")
    suspend fun rateTvShow(
        @Body rateRequest: RateRequest, @Path("tv_id") tvShowId: Int,
    ): Response<StatusResponse>

    @GET("tv/{tv_id}/reviews")
    suspend fun getTvShowReviews(
        @Path("tv_id") tvShowId: Int
    ): Response<DataWrapperResponse<TvReviewRemoteDto>>

    @GET("tv/{tv_id}/recommendations")
    suspend fun getTvShowRecomendations(
        @Path("tv_id") tvShowId: Int
    ): Response<DataWrapperResponse<TVShowsRemoteDto>>

    @GET("tv/{tv_id}/videos")
    suspend fun getTvShowYoutubeVideoDetails(
        @Path("tv_id") tvShowId: Int
    ): Response<DataWrapperResponse<YoutubeVideoDetailsRemoteDto>>
    /// endregion

    //region my list
    @GET("account/account_id/lists")
    suspend fun getUserLists(): Response<DataWrapperResponse<UserListRemoteDto>>

    @POST("list/{list_id}/add_item")
    suspend fun postUserMedia(
        @Path("list_id") listId: Int,
        @Body mediaId: AddMediaToListRequest
    ): Response<StatusResponse>

    @POST("list")
    suspend fun createUserList(@Body name: CreateUserListRequest): Response<StatusResponse>



    @GET("account/{account_id}/favorite/movies")
    suspend fun getFavoriteMovies(): Response<DataWrapperResponse<MovieRemoteDto>>

    @GET("account/{account_id}/favorite/{media_type}")
    suspend fun getFavoriteByMediaType(
        @Path("media_type") mediaType: String,
    ): Response<DataWrapperResponse<MovieRemoteDto>>


    @POST("account/{account_id}/favorite")
    suspend fun addFavoriteMovie(@Body markAsFavorite: FavoriteRequest): Response<MovieResponse>


    @GET("account/{account_id}/watchlist/{media_type}")
    suspend fun getWatchlistByMediaType(
        @Path("media_type") mediaType: String,
    ): Response<DataWrapperResponse<MovieRemoteDto>>

    @GET("account/{account_id}/watchlist/movies")
    suspend fun getWatchlist(): Response<DataWrapperResponse<MovieRemoteDto>>


    @POST("account/{account_id}/watchlist")
    suspend fun addWatchlist(
        @Body watchlistRequest: WatchlistRequest,
    ): Response<StatusResponse>


    @POST("list")
    suspend fun addList(@Body listRequest: ListRequest ): Response<ListResponse>

    @GET("account/{account_id}/lists")
    suspend fun getLists(): Response<DataWrapperResponse<ListRemoteDto>>


    @GET("list/{list_id}/add_item")
    suspend fun addMovieToList(@Body mediaId: Int): Response<MovieResponse>

    @GET("list/{list_id}")
    suspend fun getDetailsList(@Path("list_id") listId: Int)
    : Response<ListDetailsWrapperResponse<MovieItemListRemoteDto>>


    @GET("movie/{movieId}/reviews")
    suspend fun getMovieReviews(
        @Path("movieId") movieId: Int,
        @Query("page") page: Int = 1
    ): Response<ReviewsDto>

    @POST("account/account_id/favorite")
    suspend fun addFavorite(@Body markAsFavorite: FavoriteRequest): Response<StatusResponse>
    //endregion

    /// region episode
    @GET("tv/{series_id}/season/{season_number}/episode/{episode_number}")
    suspend fun getEpisodeDetails(
        @Path("series_id") seriesId: Int,
        @Path("season_number") seasonNumber: Int,
        @Path("episode_number") episodeNumber: Int
    ): Response<EpisodeDetailsRemoteDto>


    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("tv/{series_id}/season/{season_number}/episode/{episode_number}/rating")
    suspend fun postEpisodeRating(
        @Body rate: RatingEpisodeDetailsRequest,
        @Path("series_id") seriesId: Int,
        @Path("season_number") seasonNumber: Int,
        @Path("episode_number") episodeNumber: Int
    ): Response<RatingEpisodeDetailsRemoteDto>

    @GET("tv/{series_id}/season/{season_number}/episode/{episode_number}/credits")
    suspend fun getEpisodeCast(
        @Path("series_id") seriesId: Int,
        @Path("season_number") seasonNumber: Int,
        @Path("episode_number") episodeNumber: Int
    ): Response<EpisodeDetailsCastRemoteDto>

///endregion

    /// region trailer

    /// endregion
}
