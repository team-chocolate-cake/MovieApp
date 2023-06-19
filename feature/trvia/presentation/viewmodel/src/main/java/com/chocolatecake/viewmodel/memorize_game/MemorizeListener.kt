package com.chocolatecake.viewmodel.memorize_game

import com.chocolatecake.bases.BaseInteractionListener

interface MemorizeListener : BaseInteractionListener {
    fun onItemClick(itemGameImageUiState: ItemGameImageUiState)
}