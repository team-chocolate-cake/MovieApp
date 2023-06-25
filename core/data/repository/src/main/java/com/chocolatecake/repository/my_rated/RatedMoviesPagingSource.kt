package com.chocolatecake.repository.my_rated

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chocolatecake.entities.my_rated.MyRatedMovieEntity
import com.chocolatecake.remote.response.dto.my_rated.MyRatedMoviesResponseDto
import com.chocolatecake.remote.service.MovieService
import com.chocolatecake.repository.mappers.domain.my_rated.DomainMyRatedMoviesMapper
import java.io.IOException
import javax.inject.Inject


class RatedMoviesPagingSource @Inject constructor(
    private val service: MovieService,
    private val mapper: DomainMyRatedMoviesMapper
) : PagingSource<Int, MyRatedMovieEntity>() {
    suspend fun fetchData(page: Int): MyRatedMoviesResponseDto? {
        return service.getRatedMovies(page).body()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyRatedMovieEntity> {
        val currentPage = params.key ?: 1
        val response = fetchData(currentPage)
        val myRatedMovieDtos = response?.movies?.filterNotNull() ?: emptyList()
        val nextKey = if (response?.totalPages!! <= currentPage) {
            currentPage + 1
        } else {
            null
        }
        return try {
            LoadResult.Page(
                data = myRatedMovieDtos.map { mapper.map(it) },
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