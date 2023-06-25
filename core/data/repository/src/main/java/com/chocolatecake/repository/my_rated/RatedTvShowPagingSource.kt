package com.chocolatecake.repository.my_rated

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chocolatecake.entities.my_rated.MyRatedTvShowEntity
import com.chocolatecake.local.database.MovieDao
import com.chocolatecake.remote.response.dto.my_rated.MyRatedTvShowResponseDto
import com.chocolatecake.remote.service.MovieService
import com.chocolatecake.repository.mappers.domain.DomainGenreMapper
import com.chocolatecake.repository.mappers.domain.my_rated.DomainMyRatedTvShowMapper
import java.io.IOException
import javax.inject.Inject

//Todo when gohary fix basePagingSource
class RatedTvShowPagingSource @Inject constructor(
    private val service: MovieService,
    private val domainGenreMapper: DomainGenreMapper,
    private val movieDao: MovieDao,
) : PagingSource<Int, MyRatedTvShowEntity>(){


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyRatedTvShowEntity> {
        val currentPage = params.key ?: 1
        val response = service.getRatedTv(currentPage).body()
        val wrapper = response?.tvShows?.filterNotNull() ?: emptyList()
        val genreMovieMapper = domainGenreMapper.map(movieDao.getGenresMovies())
        val mapper = DomainMyRatedTvShowMapper(genreMovieMapper)
        val nextKey = if (response?.totalPages!! <= currentPage && response.totalPages!=1) currentPage + 1
         else null

        return try {
            LoadResult.Page(
                data = wrapper.map { mapper.map(it) },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MyRatedTvShowEntity>): Int? {
        return null
    }
}