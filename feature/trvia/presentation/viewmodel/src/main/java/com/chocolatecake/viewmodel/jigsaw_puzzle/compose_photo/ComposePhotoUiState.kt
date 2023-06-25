package com.chocolatecake.viewmodel.jigsaw_puzzle.compose_photo

data class ComposePhotoUiState(
    val puzzlePieces: List<PuzzlePieceImage> = mutableListOf(),
    val isFinished: Boolean = false
)