package com.chocolatecake.entities

data class BoardEntity(
    val itemsEntity: List<ItemBoardEntity>,
    val pairOfCorrectPositions: Pair<Int, Int>
){
    data class ItemBoardEntity(
        val imageUrl: String,
        val position: Int,
    )
}
