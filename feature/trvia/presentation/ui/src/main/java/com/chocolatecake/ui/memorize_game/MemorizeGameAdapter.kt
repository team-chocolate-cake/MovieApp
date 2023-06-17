package com.chocolatecake.ui.memorize_game

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.viewmodel.memorize_game.ItemGameImageUiState
import com.chocolatecake.viewmodel.memorize_game.MemorizeListener

class MemorizeGameAdapter(
    private val list: List<ItemGameImageUiState>,
    private val listener: MemorizeListener
) : BaseAdapter<ItemGameImageUiState>(list, listener) {
    override val layoutID: Int = R.layout.item_memorize
}