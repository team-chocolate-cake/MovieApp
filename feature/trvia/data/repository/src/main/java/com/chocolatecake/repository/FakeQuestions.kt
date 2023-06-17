package com.chocolatecake.repository

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeQuestions @Inject constructor() {
    private val peopleQuestions = listOf(
        "Which of the following names for this actor is correct?" to QuestionType.NAME
    )

    private val movieQuestions = listOf(
        "Which of the following names for this movie is correct?" to QuestionType.NAME,
        "Which of the following genres for this movie is correct?" to QuestionType.GENRE,
        "Which of the following rates for this movie is correct?" to QuestionType.RATE,
    )

    private val tvQuestions = listOf(
        "Which of the following names for this tv is correct?" to QuestionType.NAME,
        "Which of the following genres for this tv is correct?" to QuestionType.GENRE,
        "Which of the following rates for this tv is correct?" to QuestionType.RATE,
    )

    fun getPeopleQuestion(): Pair<String, QuestionType> {
        return peopleQuestions.first()
    }

    fun getMovieQuestion(level: Int): Pair<String, QuestionType> {
        return when (level) {
            1, 2 -> movieQuestions[level - 1]
            else -> movieQuestions[2]
        }
    }

    fun getTvQuestion(level: Int): Pair<String, QuestionType> {
        return when (level) {
            1, 2 -> tvQuestions[level - 1]
            else -> tvQuestions[2]
        }
    }

    companion object {
        enum class QuestionType {
            NAME,
            RATE,
            GENRE,
        }
    }
}