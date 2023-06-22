package com.chocolatecake.viewmodel.tv_details.mappers

import com.chocolatecake.entities.UserListEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiState
import javax.inject.Inject

class UserListsUiMapper @Inject constructor() : Mapper<List<UserListEntity>, TvDetailsUiState> {
    override fun map(input: List<UserListEntity>): TvDetailsUiState {
        return TvDetailsUiState(
            userLists = input.map(::mapUserListToUi)
        )
    }

    private fun mapUserListToUi(userListEntity: UserListEntity): TvDetailsUiState.UserListUi {
        return TvDetailsUiState.UserListUi(
            id = userListEntity.id,
            name = userListEntity.name
        )
    }
}