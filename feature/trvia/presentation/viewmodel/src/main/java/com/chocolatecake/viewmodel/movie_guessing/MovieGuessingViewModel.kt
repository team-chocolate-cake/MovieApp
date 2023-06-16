package com.chocolatecake.viewmodel.movie_guessing

import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.viewmodel.common.model.GameUIEvent
import com.chocolatecake.viewmodel.common.model.GameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieGuessingViewModel @Inject constructor(

) : BaseViewModel<GameUiState, GameUIEvent>(GameUiState()) {

}