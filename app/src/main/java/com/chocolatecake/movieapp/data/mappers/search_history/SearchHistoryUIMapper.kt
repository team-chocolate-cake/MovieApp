package com.chocolatecake.movieapp.data.mappers.search_history

import com.chocolatecake.movieapp.data.local.database.dto.SearchHistoryLocalDto
import com.chocolatecake.movieapp.data.mappers.Mapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SearchHistoryUIMapper @Inject constructor() : Mapper<SearchHistoryLocalDto, SearchHistory> {
    override fun map(input: SearchHistoryLocalDto): SearchHistory {
        return SearchHistory(
            keyword = input.keyword,
        )
    }
}