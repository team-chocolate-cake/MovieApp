package com.chocolatecake.viewmodel.model

data class CommentUi(
    val name: String,
    val content: String,
) {
    fun getFirstTwoCharsFromName(): String {
        return name.split(" ").map {
            it.first()
        }.joinToString("")
    }
}
