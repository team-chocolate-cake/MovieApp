package com.chocolatecake.bases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE ,EVENT> : ViewModel() {
    protected val _state: MutableStateFlow<STATE> by lazy {  MutableStateFlow(initialState()) }
    protected abstract fun initialState(): STATE
    val state = _state.asStateFlow()

    protected val _event = Channel<EVENT>()
    val event = _event.receiveAsFlow()

    abstract fun getData()

    protected fun <T> tryToExecute(
        call: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                call().also(onSuccess)
            } catch (th: Throwable) {
                onError(th)
            }
        }
    }

    protected fun <T> tryToExecuteFlow(
        call: suspend () -> Flow<T>,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                call().collect(onSuccess)
            } catch (th: Throwable) {
                onError(th)
            }
        }
    }

    protected fun sendEvent(event: EVENT){
        viewModelScope.launch { _event.send(event) }
    }
}