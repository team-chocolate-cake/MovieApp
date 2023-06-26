package com.chocolatecake.repository.my_rated

import com.chocolatecake.entities.my_rated.MyRatedTvShowEntity
import com.chocolatecake.local.database.MovieDao
import com.chocolatecake.remote.service.MovieService
import com.chocolatecake.repository.BasePagingSource
import com.chocolatecake.repository.mappers.domain.DomainGenreMapper
import com.chocolatecake.repository.mappers.domain.my_rated.DomainMyRatedTvShowMapper
import javax.inject.Inject

//Todo when gohary fix basePagingSource
class RatedTvShowPagingSource @Inject constructor(
    service: MovieService,
    private val domainGenreMapper: DomainGenreMapper,
    private val movieDao: MovieDao,
) : BasePagingSource<MyRatedTvShowEntity>(service){
    override suspend fun fetchData(page: Int): List<MyRatedTvShowEntity> {
        val response = service.getRatedTv(page).body()?.tvShows?.filterNotNull()
        val genreMovieMapper = domainGenreMapper.map(movieDao.getGenresMovies())
        val mapper = DomainMyRatedTvShowMapper(genreMovieMapper)
        return response?.map { mapper.map(it) } ?: emptyList()
    }
}