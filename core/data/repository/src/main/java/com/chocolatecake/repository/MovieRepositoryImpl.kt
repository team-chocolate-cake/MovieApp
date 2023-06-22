package com.chocolatecake.repository

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.entities.ReviewEntity
import com.chocolatecake.entities.SeasonEntity
import com.chocolatecake.entities.StatusEntity
import com.chocolatecake.entities.TvDetailsInfoEntity
import com.chocolatecake.entities.TvShowEntity
import com.chocolatecake.entities.UserListEntity
import com.chocolatecake.entities.YoutubeVideoDetailsEntity
import com.chocolatecake.local.database.MovieDao
import com.chocolatecake.local.database.dto.SearchHistoryLocalDto
import com.chocolatecake.remote.request.AddMediaToListRequest
import com.chocolatecake.remote.request.CreateUserListRequest
import com.chocolatecake.remote.request.RateRequest
import com.chocolatecake.remote.response.dto.YoutubeVideoDetailsRemoteDto
import com.chocolatecake.remote.service.MovieService
import com.chocolatecake.repository.mappers.cash.LocalGenresMovieMapper
import com.chocolatecake.repository.mappers.cash.LocalPopularPeopleMapper
import com.chocolatecake.repository.mappers.cash.movie.LocalNowPlayingMovieMapper
import com.chocolatecake.repository.mappers.cash.movie.LocalPopularMovieMapper
import com.chocolatecake.repository.mappers.cash.movie.LocalTopRatedMovieMapper
import com.chocolatecake.repository.mappers.cash.movie.LocalTrendingMoviesMapper
import com.chocolatecake.repository.mappers.cash.movie.LocalUpcomingMovieMapper
import com.chocolatecake.repository.mappers.domain.DomainGenreMapper
import com.chocolatecake.repository.mappers.domain.DomainPeopleMapper
import com.chocolatecake.repository.mappers.domain.DomainStatusMapper
import com.chocolatecake.repository.mappers.domain.DomainTvDetailsCreditMapper
import com.chocolatecake.repository.mappers.domain.DomainTvDetailsMapper
import com.chocolatecake.repository.mappers.domain.DomainTvDetailsReviewMapper
import com.chocolatecake.repository.mappers.domain.DomainTvDetailsSeasonMapper
import com.chocolatecake.repository.mappers.domain.DomainTvShowMapper
import com.chocolatecake.repository.mappers.domain.DomainUserListsMapper
import com.chocolatecake.repository.mappers.domain.DomainYoutubeDetailsMapper
import com.chocolatecake.repository.mappers.domain.movie.DomainNowPlayingMovieMapper
import com.chocolatecake.repository.mappers.domain.movie.DomainPopularMovieMapper
import com.chocolatecake.repository.mappers.domain.movie.DomainTopRatedMovieMapper
import com.chocolatecake.repository.mappers.domain.movie.DomainTrendingMoviesMapper
import com.chocolatecake.repository.mappers.domain.movie.DomainUpcomingMovieMapper
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val movieDao: MovieDao,
    private val localGenresMovieMapper: LocalGenresMovieMapper,
    private val localPopularMovieMapper: LocalPopularMovieMapper,
    private val localPopularPeopleMapper: LocalPopularPeopleMapper,
    private val localNowPlayingMovieMapper: LocalNowPlayingMovieMapper,
    private val localTopRatedMovieMapper: LocalTopRatedMovieMapper,
    private val localTrendingMoviesMapper: LocalTrendingMoviesMapper,
    private val localUpcomingMovieMapper: LocalUpcomingMovieMapper,
    private val domainPopularMovieMapper: DomainPopularMovieMapper,
    private val domainNowPlayingMovieMapper: DomainNowPlayingMovieMapper,
    private val domainTopRatedMovieMapper: DomainTopRatedMovieMapper,
    private val domainUpcomingMovieMapper: DomainUpcomingMovieMapper,
    private val domainTrendingMovieMapper: DomainTrendingMoviesMapper,
    private val domainPeopleMapper: DomainPeopleMapper,
    private val domainGenreMapper: DomainGenreMapper,
) : BaseRepository(), MovieRepository {

    /// region movies
    override suspend fun getPopularMovies(): List<MovieEntity> {
        return domainPopularMovieMapper.map(movieDao.getPopularMovies())
    }

    override suspend fun refreshPopularMovies() {
        refreshWrapper(
            movieService::getPopularMovies,
            localPopularMovieMapper::map,
            movieDao::insertPopularMovies
        )
    }

    override suspend fun getNowPlayingMovies(): List<MovieEntity> {
        return domainNowPlayingMovieMapper.map(movieDao.getNowPlayingMovies())
    }

    override suspend fun refreshNowPlayingMovies() {
        refreshWrapper(
            movieService::getNowPlayingMovies,
            localNowPlayingMovieMapper::map,
            movieDao::insertNowPlayingMovies
        )
    }

    override suspend fun getTopRatedMovies(): List<MovieEntity> {
        return domainTopRatedMovieMapper.map(movieDao.getTopRatedMovies())
    }

    override suspend fun refreshTopRatedMovies() {
        refreshWrapper(
            movieService::getTopRatedMovies,
            localTopRatedMovieMapper::map,
            movieDao::insertTopRatedMovies
        )
    }

    override suspend fun getUpcomingMovies(): List<MovieEntity> {
        return domainUpcomingMovieMapper.map(movieDao.getUpcomingMovies())
    }

    override suspend fun refreshUpcomingMovies() {
        refreshWrapper(
            movieService::getUpcomingMovies,
            localUpcomingMovieMapper::map,
            movieDao::insertUpcomingMovies
        )
    }

    override suspend fun getTrendingMovies(): List<MovieEntity> {
        return domainTrendingMovieMapper.map(movieDao.getTrendingMovies())
    }

    override suspend fun refreshTrendingMovies() {
        refreshWrapper(
            movieService::getTrendingMovies,
            localTrendingMoviesMapper::map,
            movieDao::insertTrendingMovies
        )
    }

    override suspend fun getPopularPeople(): List<PeopleEntity> {
        return domainPeopleMapper.map(movieDao.getPopularPeople())
    }

    override suspend fun refreshPopularPeople() {
        refreshWrapper(
            movieService::getPopularPeople,
            localPopularPeopleMapper::map,
            movieDao::insertPopularPeople
        )
    }
    /// endregion

    /// region search history
    override suspend fun getSearchHistory(keyword: String): List<String> {
        return movieDao.getSearchHistory("%${keyword}%").map { it.keyword }

    }

    override suspend fun insertSearchHistory(keyword: String) {
        return movieDao.insertSearchHistory(SearchHistoryLocalDto(keyword))
    }

    override suspend fun clearAllSearchHistory() {
        return movieDao.clearAllSearchHistory()
    }

    override suspend fun deleteSearchHistory(keyword: String) {
        movieDao.deleteSearchHistory(keyword)
    }
    /// endregion

    ///region search
    override suspend fun getSearchMovies(keyword: String): List<MovieEntity> {
        val genresEntities = getGenresMovies()
        return wrapApiCall { movieService.getSearchMovies(keyword) }.results
            ?.filterNotNull()?.let { movieDtos ->
                movieDtos.map { input ->
                    MovieEntity(
                        id = input.id ?: 0,
                        rate = input.voteAverage ?: 0.0,
                        title = input.title ?: "",
                        genreEntities = filterGenres(
                            input.genreIds?.filterNotNull() ?: emptyList(),
                            genresEntities
                        ),
                        imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
                    )
                }
            } ?: emptyList()
    }

    private fun filterGenres(
        genresIds: List<Int>,
        genresEntities: List<GenreEntity>
    ): List<GenreEntity> = genresEntities.filter { it.genreID in genresIds }
    //endregion

    /// region genres
    override suspend fun getGenresMovies(): List<GenreEntity> {
        return domainGenreMapper.map(movieDao.getGenresMovies())
    }

    override suspend fun refreshGenres() {
        try {
            wrapApiCall { movieService.getListOfGenresForMovies() }.results
                ?.let { remoteGenres ->
                    movieDao.insertGenresMovies(localGenresMovieMapper.map(remoteGenres))
                }

        } catch (_: Throwable) {
        }
    }

    /// endregion

    ///region tv
    override suspend fun getTvDetailsInfo(tvShowID: Int): TvDetailsInfoEntity {
        return DomainTvDetailsMapper().map(wrapApiCall { movieService.getTvDetails(tvShowID) })
    }

    override suspend fun getTvDetailsCredit(tvShowID: Int): List<PeopleEntity> {
        return DomainTvDetailsCreditMapper().map(
            wrapApiCall {
                movieService.getTvDetailsCredit(tvShowID)
            })
    }

    override suspend fun rateTvShow(rate: Double, tvShowID: Int): StatusEntity {
        val newRate = RateRequest(value = rate)
        return DomainStatusMapper().map(wrapApiCall {
            movieService.rateTvShow(
                newRate,
                tvShowID
            )
        })
    }

    override suspend fun getTvShowReviews(tvShowID: Int): List<ReviewEntity> {
        val call = wrapApiCall { movieService.getTvShowReviews(tvShowID) }.results?.filterNotNull()
            ?: emptyList()
        return DomainTvDetailsReviewMapper().map(call)

    }

    override suspend fun getTvShowRecommendations(tvShowID: Int): List<TvShowEntity> {
        val call =
            wrapApiCall { movieService.getTvShowRecomendations(tvShowID) }.results?.filterNotNull()
                ?: emptyList()
        return DomainTvShowMapper().map(call)
    }

    override suspend fun getTvShowYoutubeDetails(tvShowID: Int): YoutubeVideoDetailsEntity {
        val call =
            wrapApiCall { movieService.getTvShowYoutubeVideoDetails(tvShowID) }.results?.first()
                ?: YoutubeVideoDetailsRemoteDto()
        return DomainYoutubeDetailsMapper().map(call)
    }


    override suspend fun getTvDetailsSeasons(tvShowID: Int): List<SeasonEntity> {
        return DomainTvDetailsSeasonMapper().map(wrapApiCall { movieService.getTvDetails(tvShowID) })
    }


    /// endregion
    override suspend fun getUserLists(): List<UserListEntity> {
        val call =
            wrapApiCall { movieService.getUserLists() }.results?.filterNotNull() ?: emptyList()
        return DomainUserListsMapper().map(call)
    }

    override suspend fun postUserLists(listId: Int, mediaId: Int): StatusEntity {
        val addMediaRequest = AddMediaToListRequest(mediaId)
        val call = wrapApiCall { movieService.postUserMedia(listId, addMediaRequest) }
        return DomainStatusMapper().map(call)
    }

    override suspend fun createUserList(listName: String): StatusEntity {
        val newList = CreateUserListRequest(listName)
        val call = wrapApiCall { movieService.createUserList(newList) }
        return DomainStatusMapper().map(call)
    }
}
