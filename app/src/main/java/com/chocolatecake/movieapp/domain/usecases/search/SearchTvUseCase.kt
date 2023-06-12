package com.chocolatecake.movieapp.domain.usecases.search

import com.chocolatecake.movieapp.BuildConfig
import com.chocolatecake.movieapp.data.remote.response.TvDto
import com.chocolatecake.movieapp.data.repository.MovieRepository
import com.chocolatecake.movieapp.domain.mappers.Mapper
import com.chocolatecake.movieapp.ui.search.SearchUiState
import javax.inject.Inject


class SearchTvUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvMapper: TvMapper
) {
    suspend operator fun invoke(keyword: String): List<SearchUiState.MediaUIState> {
        val tvs = movieRepository.searchForTv(keyword)
        return tvs.filter { it.voteAverage != 0.0 }.sortedByDescending { it.voteAverage }.map {tv->
            val formattedVoteAverage = "%.1f".format(tv.voteAverage)
            tvMapper.map(tv.copy(voteAverage = formattedVoteAverage.toDouble()))
        }
    }
}

class TvMapper @Inject constructor() : Mapper<TvDto, SearchUiState.MediaUIState> {
    override fun map(input: TvDto): SearchUiState.MediaUIState {
        return SearchUiState.MediaUIState(
            mediaId = input.id ?: 0,
            mediaRate = input.voteAverage ?: 0.0,
            mediaName  = input.name ?: "",
            mediaImage = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
        )
    }
}