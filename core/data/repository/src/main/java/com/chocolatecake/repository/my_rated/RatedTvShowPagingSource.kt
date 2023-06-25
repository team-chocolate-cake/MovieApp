package com.chocolatecake.repository.my_rated

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chocolatecake.entities.my_rated.MyRatedTvShowEntity
import com.chocolatecake.remote.response.dto.my_rated.MyRatedTvShowResponseDto
import com.chocolatecake.remote.service.MovieService
import com.chocolatecake.repository.mappers.domain.my_rated.DomainMyRatedTvShowMapper
import java.io.IOException
import javax.inject.Inject


class RatedTvShowPagingSource @Inject constructor(
    private val service: MovieService,
    private val mapper: DomainMyRatedTvShowMapper
) : PagingSource<Int, MyRatedTvShowEntity>(){
     suspend fun fetchData(page: Int): MyRatedTvShowResponseDto? {
        return service.getRatedTv(page).body()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyRatedTvShowEntity> {
        val currentPage = params.key ?: 1
        val response = fetchData(currentPage)
        val myRatedTvShowsDtos = response?.tvShows?.filterNotNull() ?: emptyList()
        val nextKey = if (response?.totalPages!! <= currentPage && response.totalPages!=1) {
            currentPage + 1
        } else {
            null
        }
        return try {
            LoadResult.Page(
                data = myRatedTvShowsDtos.map { mapper.map(it) },
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