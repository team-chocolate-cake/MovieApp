package com.chocolatecake.viewmodel.common.model

data class ItemGameCardUIState(
    val title: String = "Level 1",
    val level: String = "Easy",
    val questionsCount: Int = 0,
    val max: Int = 5,
    val caption: String? = null,
    val isOpenLevel: Boolean,
) {
    val progress
        get() = questionsCount / max

    val missedQuestionsCount
        get() = max - progress

    val formattedProgress
        get() = "$progress%"
}
