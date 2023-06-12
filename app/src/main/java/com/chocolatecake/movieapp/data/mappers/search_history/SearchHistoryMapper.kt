package com.chocolatecake.movieapp.data.mappers.search_history
import com.chocolatecake.movieapp.data.local.database.dto.SearchHistoryLocalDto
import com.chocolatecake.movieapp.data.mappers.Mapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SearchHistoryMapper @Inject constructor() : Mapper<SearchHistory, SearchHistoryLocalDto> {
    override fun map(input: SearchHistory): SearchHistoryLocalDto {
        return SearchHistoryLocalDto(
            keyword = input.keyword,
        )
    }
}