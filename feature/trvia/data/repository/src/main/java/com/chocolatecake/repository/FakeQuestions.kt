package com.chocolatecake.repository

import android.content.Context
import javax.inject.Inject
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
class FakeQuestions @Inject constructor(
    @ApplicationContext private val context:Context
) {
    private val peopleQuestions = listOf(
        context.getString(R.string.which_of_the_following_names_for_this_actor_is_correct) to QuestionType.NAME
    )

    private val movieQuestions = listOf(
        context.getString(R.string.which_of_the_following_names_for_this_movie_is_correct) to QuestionType.NAME,
        context.getString(R.string.which_of_the_following_genres_for_this_movie_is_correct) to QuestionType.GENRE,
        context.getString(R.string.which_of_the_following_rates_for_this_movie_is_correct) to QuestionType.RATE,
    )

    private val tvQuestions = listOf(
        context.getString(R.string.which_of_the_following_names_for_this_tv_is_correct) to QuestionType.NAME,
        context.getString(R.string.which_of_the_following_genres_for_this_tv_is_correct) to QuestionType.GENRE,
        context.getString(R.string.which_of_the_following_rates_for_this_tv_is_correct) to QuestionType.RATE,
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