package com.chocolatecake.movieapp.data.mappers.search_history
import com.chocolatecake.movieapp.data.local.database.entity.SearchHistoryEntity
import com.chocolatecake.movieapp.data.mappers.Mapper
import com.chocolatecake.movieapp.domain.model.SearchHistory
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SearchHistoryMapper @Inject constructor() : Mapper<SearchHistory, SearchHistoryEntity> {
    override fun map(input: SearchHistory): SearchHistoryEntity {
        return SearchHistoryEntity(
            keyword = input.keyword,
        )
    }
}