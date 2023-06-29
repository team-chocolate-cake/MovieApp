package com.chocolatecake.viewmodel.common.model

import com.chocolatecake.bases.StringsRes
import kotlin.math.roundToInt


data class ItemGameLevelUIState (
    private val stringRes: StringsRes,
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
            1 -> stringRes.easy
            2 -> stringRes.medium
            else -> stringRes.hard
        }
}
