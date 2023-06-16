package com.chocolatecake.viewmodel.common.profile

import com.chocolatecake.entities.ProfileEntity
import com.chocolatecake.mapper.Mapper
import javax.inject.Inject

class ProfileUiMapper @Inject constructor() : Mapper<ProfileEntity,ProfileUIState> {
    override fun map(input: ProfileEntity): ProfileUIState {
        return ProfileUIState(
            input.username,
            input.avatarUrl
        )
    }
}