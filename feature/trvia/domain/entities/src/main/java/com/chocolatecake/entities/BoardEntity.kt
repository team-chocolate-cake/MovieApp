package com.chocolatecake.entities

data class BoardEntity(
    val itemsEntity: List<ItemBoardEntity>,
){
    data class ItemBoardEntity(
        val imageUrl: String,
        val position: Int,
    )
}
