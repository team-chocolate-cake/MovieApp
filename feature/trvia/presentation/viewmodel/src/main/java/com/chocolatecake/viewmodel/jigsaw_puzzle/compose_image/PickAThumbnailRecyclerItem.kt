package com.chocolatecake.viewmodel.jigsaw_puzzle.compose_image

sealed class PickAThumbnailRecyclerItem {
    data class Title(val text: String) : PickAThumbnailRecyclerItem()
    data class Card(val item: CardItem) : PickAThumbnailRecyclerItem()
}

