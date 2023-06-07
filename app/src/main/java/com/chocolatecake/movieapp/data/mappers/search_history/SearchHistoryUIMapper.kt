package com.chocolatecake.movieapp.data.mappers.search_history

import com.chocolatecake.movieapp.data.local.database.entity.SearchHistoryEntity
import com.chocolatecake.movieapp.data.mappers.Mapper
import com.chocolatecake.movieapp.domain.model.SearchHistory
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SearchHistoryUIMapper @Inject constructor() : Mapper<SearchHistoryEntity, SearchHistory> {
    override fun map(input: SearchHistoryEntity): SearchHistory {
        return SearchHistory(
            keyword = input.keyword,
        )
    }
}