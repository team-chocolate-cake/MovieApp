package com.chocolatecake.viewmodel.common.model

data class CommentUIState(
    val name: String,
    val content: String,
) {
    fun getFirstTwoCharsFromName(): String {
        return name.split(" ").map {
            it.first()
        }.joinToString("")
    }
}
