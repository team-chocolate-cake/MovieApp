package com.chocolatecake.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chocolatecake.remote.service.MovieService
import retrofit2.Response
import java.net.UnknownHostException

abstract class BasePagingSource<Value : Any>(
    val service: MovieService
) : PagingSource<Int, Value>() {

    protected abstract suspend fun fetchData(page: Int): List<Value>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        val currentPage = params.key ?: 1
        return try {
            val response = wrapPagingApiCall { fetchData(currentPage) }
            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = currentPage + 1
            )
        } catch (e: NoNetworkThrowable) {
            Log.d("network---base", e.message.toString())
            LoadResult.Error(e)
        } catch (e: Exception) {
            Log.d("network---base", e.message.toString())
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return null
    }

    protected suspend fun <T> wrapPagingApiCall(call: suspend () -> List<T>): List<T> {
        return try {
            call()
        } catch (e: NoNetworkThrowable) {
            throw e
        }
    }

}