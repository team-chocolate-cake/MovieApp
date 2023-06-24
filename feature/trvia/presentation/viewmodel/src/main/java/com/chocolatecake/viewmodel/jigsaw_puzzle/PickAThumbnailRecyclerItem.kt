package com.chocolatecake.viewmodel.jigsaw_puzzle

sealed class PickAThumbnailRecyclerItem {
    data class Title(val text: String) : PickAThumbnailRecyclerItem()
    data class Card(val item: CardItem) : PickAThumbnailRecyclerItem()
}

