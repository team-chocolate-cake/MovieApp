package com.chocolatecake.viewmodel.congrats

import com.chocolatecake.bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CongratsViewModel @Inject constructor(

) : BaseViewModel<CongratsUIState,CongratsUIEvent>(CongratsUIState()), CongratsListener {

    override fun onClickNextLevel() {
        sendEvent(CongratsUIEvent.NavigateToNextLevel)
    }

    override fun onClickReturn() {
        sendEvent(CongratsUIEvent.NavigateToHome)
    }

}