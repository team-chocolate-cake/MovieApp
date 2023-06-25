package com.chocolatecake.viewmodel.jigsaw_puzzle.compose_photo

import com.chocolatecake.bases.BaseViewModel

class ComposePhotoViewModel
    :BaseViewModel<ComposePhotoUiState,ComposePhotoUiEvent>(ComposePhotoUiState()) {


    fun checkGameOver() {
        if (isGameFinished()) {
          sendEvent(ComposePhotoUiEvent.GameFinished)
        }
    }
    private fun isGameFinished(): Boolean {
        for (piece in state.value.puzzlePieces){
            if (piece.canMove) {
                return false
            }
        }
        return true
    }
}