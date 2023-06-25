package com.chocolatecake.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chocolatecake.remote.service.MovieService
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

abstract class BasePagingSource<Value : Any>(
    val service: MovieService
) : PagingSource<Int, Value>() {

    protected abstract suspend fun fetchData(page: Int): List<Value>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        val currentPage = params.key ?: 1
        return try {
            val response = fetchData(currentPage)
            val nextKey = if (response.lastIndex >= currentPage) currentPage + 1 else null
            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return null
    }
}
