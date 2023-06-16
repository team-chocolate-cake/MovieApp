package com.chocolatecake.viewmodel.common.model

data class GameUiState(
    val gameType: GameType = GameType.PEOPLE,
    val CountDownTimer: Int = 30,
    val level: Int = 1,
    val questionCount: Int = 1,
    val totalQuestions: Int = 5,
    val heartCount: Int = 3,
    val points: Int = 0,
    val question: String = "",
    val answers: List<String> = emptyList(),
    val correctAnswerPosition: Int = 0,
    val imageUrl: String = "",
    val userAnswer: Int? = null


)

enum class GameType{
    PEOPLE,
    MOVIE,
    TV_SHOW,
    MEMORIZE
}
