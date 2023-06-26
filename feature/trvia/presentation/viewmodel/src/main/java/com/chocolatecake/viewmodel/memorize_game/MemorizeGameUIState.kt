package com.chocolatecake.viewmodel.memorize_game

data class MemorizeGameUIState(
    val initialCounter: Int = 30,
    val countDownTimer: Int = 30,
    val level: Int = 1,
    val board: List<ItemGameImageUiState> = emptyList(),
    val initialBoard: List<ItemGameImageUiState> = emptyList(),
    val selectedUserPositions: List<Int> = emptyList(),
    val boardSize: Int = 12,
    val points: Int = 0,
    val firstUserPosition: Int? = null,
    val secondUserPosition: Int? = null,
    val isLoading: Boolean = true,
    val isError: Boolean = false
){
    val isWinner: Boolean
        get() = boardSize == selectedUserPositions.size

}

data class ItemGameImageUiState(
    val imageUrl: String = "",
    val position: Int = 0,
    val isSelected: Boolean = false
)
