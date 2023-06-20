package com.chocolatecake.viewmodel.common.model

import kotlin.math.roundToInt

data class ItemGameLevelUIState(
    val level: Int = 1,
    val questionsCount: Int = 0,
    val max: Int = 5,
    val isOpenLevel: Boolean,
    val hasProgress: Boolean = true,
    val howToPass: (level: Int) -> Boolean = { true }
) {

    val progress: Int
        get() = if (hasProgress) ((questionsCount.toDouble() / max) * 100.0).roundToInt() else 0

    val formattedProgress
        get() = "$progress%"

    val isPassed: Boolean
        get() = if (hasProgress) progress == 100 else howToPass(level)

    val title
        get() = when (level) {
            1 -> "Easy"
            2 -> "Medium"
            else -> "Hard"
        }
}
