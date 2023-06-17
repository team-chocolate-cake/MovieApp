package com.chocolatecake.viewmodel.profile

import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.profile.GetAccountDetailsUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getAccountDetailsUseCase: GetAccountDetailsUseCase,
    private val profileUiMapper: ProfileUiMapper
): BaseViewModel <ProfileUIState, ProfileUiEvent>(ProfileUIState())
{
    private fun getAccountDetails(){
        viewModelScope.launch {
            profileUiMapper.map(getAccountDetailsUseCase())
        }
    }
}