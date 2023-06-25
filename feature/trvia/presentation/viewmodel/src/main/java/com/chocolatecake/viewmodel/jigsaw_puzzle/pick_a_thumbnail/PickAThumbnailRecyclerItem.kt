package com.chocolatecake.viewmodel.jigsaw_puzzle.pick_a_thumbnail

sealed class PickAThumbnailRecyclerItem {
    data class Title(val text: String) : PickAThumbnailRecyclerItem()
    data class Card(val item: CardItem) : PickAThumbnailRecyclerItem()
}

