package com.chocolatecake.repository.my_rated

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chocolatecake.entities.my_rated.MyRatedMovieEntity
import com.chocolatecake.local.database.MovieDao
import com.chocolatecake.remote.response.dto.my_rated.MyRatedMoviesResponseDto
import com.chocolatecake.remote.service.MovieService
import com.chocolatecake.repository.mappers.domain.DomainGenreMapper
import com.chocolatecake.repository.mappers.domain.my_rated.DomainMyRatedMoviesMapper
import java.io.IOException
import javax.inject.Inject

//Todo when gohary fix basePagingSource
class RatedMoviesPagingSource @Inject constructor(
    private val service: MovieService,
    private val domainGenreMapper: DomainGenreMapper,
    private val movieDao: MovieDao,
) : PagingSource<Int, MyRatedMovieEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyRatedMovieEntity> {
        val currentPage = params.key ?: 1
        val response = service.getRatedMovies(currentPage).body()
        val wrapper = response?.movies?.filterNotNull() ?: emptyList()
        val genreMovieMapper = domainGenreMapper.map(movieDao.getGenresMovies())
        val mapper = DomainMyRatedMoviesMapper(genreMovieMapper)
        val nextKey = if (response?.totalPages!! <= currentPage) {
            currentPage + 1
        } else {
            null
        }
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

    override fun getRefreshKey(state: PagingState<Int, MyRatedMovieEntity>): Int? {
        return null
    }

}