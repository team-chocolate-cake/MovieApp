package com.chocolatecake.viewmodel.jigsaw_puzzle.compose_photo

sealed interface ComposePhotoUiEvent {
    object GameFinished : ComposePhotoUiEvent
}