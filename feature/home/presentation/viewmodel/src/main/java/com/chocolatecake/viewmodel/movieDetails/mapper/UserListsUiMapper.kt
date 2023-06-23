package com.chocolatecake.viewmodel.movieDetails.mapper

import com.chocolatecake.entities.UserListEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsUiState
import javax.inject.Inject

class UserListsUiMapper @Inject constructor() : Mapper<List<UserListEntity>, MovieDetailsUiState> {
    override fun map(input: List<UserListEntity>): MovieDetailsUiState {
        return MovieDetailsUiState(
            userLists = input.map(::mapUserListToUi)
        )
    }

    private fun mapUserListToUi(userListEntity: UserListEntity): MovieDetailsUiState.UserListUi {
        return MovieDetailsUiState.UserListUi(
            id = userListEntity.id,
            name = userListEntity.name
        )
    }
}