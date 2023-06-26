package com.chocolatecake.viewmodel.common.model

data class GameUiState(
    val gameType: GameType = GameType.PEOPLE,
    val countDownTimer: Int = 30,
    val level: Int = 1,
    val questionCount: Int = 1,
    val heartCount: Int = 3,
    val points: Int = 0,
    val question: String = "",
    val answers: List<AnswerUiState> = emptyList(),
    val correctAnswerPosition: Int = 0,
    val imageUrl: String = "",
    val userAnswer: Int? = null,
    val isLoading: Boolean = true,
    val isError: Boolean = false
) {
    val totalQuestions: Int
        get() = when (level) {
            1 -> 5
            2 -> 10
            else -> 15
        }

    val isLastQuestion: Boolean get() = questionCount == totalQuestions

    data class AnswerUiState(
        val position: Int,
        val answer: String,
        val isCorrect: Boolean,
    )
}

enum class GameType{
    PEOPLE,
    MOVIE,
    TV_SHOW,
    MEMORIZE
}
