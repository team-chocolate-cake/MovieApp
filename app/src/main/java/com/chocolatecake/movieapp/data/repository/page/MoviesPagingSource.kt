package com.chocolatecake.movieapp.data.repository.page

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chocolatecake.movieapp.data.remote.response.MovieDto
import com.chocolatecake.movieapp.data.remote.service.MovieService
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.properties.Delegates


class MoviesPagingSource @Inject constructor(private val service: MovieService) :
    PagingSource<Int, MovieDto>() {

    private var movieSearchQuery by Delegates.notNull<String>()

    fun setSearchQuery(query: String){
        movieSearchQuery = query
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        val currentPage = params.key ?: 1
        return try {
            val response = service.searchForMovies(query = movieSearchQuery, page = currentPage).body()?.results?.filterNotNull()
            LoadResult.Page(
                data = requireNotNull(response),
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e: IllegalArgumentException) {
            LoadResult.Error(e)
        } catch (httpException: HttpException) {
            LoadResult.Error(httpException)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        return null
    }
}
