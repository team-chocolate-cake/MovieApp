package com.chocolatecake.entities

data class QuestionEntity(
    val question: String,
    val imageUrl: String,
    val choices: List<String>,
    val correctAnswerPosition: Int
)
