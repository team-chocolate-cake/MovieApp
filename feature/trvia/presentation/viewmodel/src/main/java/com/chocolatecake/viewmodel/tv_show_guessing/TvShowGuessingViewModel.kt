package com.chocolatecake.viewmodel.tv_show_guessing

import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.viewmodel.common.model.GameUIEvent
import com.chocolatecake.viewmodel.common.model.GameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowGuessingViewModel @Inject constructor(

): BaseViewModel<GameUiState, GameUIEvent>(GameUiState()) {

}
