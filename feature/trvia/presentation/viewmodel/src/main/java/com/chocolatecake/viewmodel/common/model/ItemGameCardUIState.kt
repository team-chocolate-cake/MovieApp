package com.chocolatecake.viewmodel.common.model

data class ItemGameCardUIState(
    val title: String = "Level 1",
    val level: String = "Easy",
    val progress: Int = 0,
    val caption: String? = null,
    val isOpenLevel: Boolean,
) {
    fun getFormattedProgress(): String {
        return "$progress%"
    }
}
