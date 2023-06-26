package com.chocolatecake.viewmodel.congrats

data class CongratsUIState(
    val level: Int = 1,
    val userName: String = "",
    val points: Int = 0,
    val isCompletedLevel:Boolean = false
){
    val isLastLevel:Boolean
        get() = level > 3
}
