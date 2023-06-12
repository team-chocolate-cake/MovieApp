package com.chocolatecake.movieapp.domain.usecases.search

import com.chocolatecake.movieapp.data.remote.response.TvDto
import com.chocolatecake.movieapp.data.repository.MovieRepository
import com.chocolatecake.movieapp.domain.mappers.Mapper
import com.chocolatecake.movieapp.domain.model.TvEntity
import javax.inject.Inject


class SearchTvUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvMapper: TvMapper
) {
    suspend operator fun invoke(keyword: String): List<TvEntity> {
        return movieRepository.searchForTv(keyword).sortedByDescending { it.voteAverage }.map {
            tvMapper.map(it)
        }
    }
}

class TvMapper @Inject constructor() : Mapper<TvDto, TvEntity> {
    override fun map(input: TvDto): TvEntity {
        return TvEntity(
            id = input.id ?: 0,
            posterPath = input.posterPath ?: "",
            name = input.name ?: "",
            voteAverage = input.voteAverage ?: 0.0
        )
    }
}