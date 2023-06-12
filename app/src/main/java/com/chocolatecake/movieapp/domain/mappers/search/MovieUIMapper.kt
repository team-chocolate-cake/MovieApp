package com.chocolatecake.movieapp.domain.mappers.search

import com.chocolatecake.movieapp.BuildConfig
import com.chocolatecake.movieapp.data.remote.response.MovieDto
import com.chocolatecake.movieapp.domain.mappers.Mapper
import com.chocolatecake.movieapp.ui.search.SearchUiState
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MovieUIMapper @Inject constructor(): Mapper<MovieDto, SearchUiState.MediaUIState> {
    override fun map(input: MovieDto): SearchUiState.MediaUIState {
        return SearchUiState.MediaUIState(
            mediaId = input.id ?: 0,
            mediaRate = input.voteAverage ?: 0.0,
            mediaName  = input.title ?: "",
            mediaImage = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
        )
    }
}