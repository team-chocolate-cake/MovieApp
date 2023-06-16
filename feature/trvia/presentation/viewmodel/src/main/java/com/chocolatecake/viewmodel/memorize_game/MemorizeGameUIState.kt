package com.chocolatecake.viewmodel.memorize_game

data class MemorizeGameUIState(
    val countDownTimer: Int = 30,
    val level: Int = 1,
    val board: List<ItemGameImageUiState> = emptyList(),
    val initialBoard: List<ItemGameImageUiState> = emptyList(),
    val boardSize: Int = 12,
    val points: Int = 0,
    val CorrectPairPositions: Pair<Int,Int> = Pair(0,1),
    val firstUserPosition: Int? = null,
    val secondUserPosition: Int? = null

)

data class ItemGameImageUiState(
    val imageUrl: String = "",
    val position: Int = 0,
    val isSelected: Boolean = false
)
