package com.chocolatecake.viewmodel.game_level.utill

import com.chocolatecake.entities.UserEntity
import com.chocolatecake.viewmodel.common.model.ItemGameLevelUIState
import javax.inject.Inject

class GameLevelGenerator @Inject constructor() {
    fun getPeopleGameLevelUISate(userEntity: UserEntity): List<ItemGameLevelUIState> {
        return userEntity.run {
            listOf(
                ItemGameLevelUIState(
                    level = 1,
                    questionsCount = if (peopleGameLevel > 1) 5 else numPeopleQuestionsPassed,
                    max = 5,
                    isOpenLevel = peopleGameLevel >= 1,
                ),
                ItemGameLevelUIState(
                    level = 2,
                    questionsCount = if (peopleGameLevel > 2) 10 else numPeopleQuestionsPassed,
                    max = 10,
                    isOpenLevel = peopleGameLevel >= 2
                ),
                ItemGameLevelUIState(
                    level = 3,
                    questionsCount = if (peopleGameLevel > 3) 15 else numPeopleQuestionsPassed,
                    max = 15,
                    isOpenLevel = peopleGameLevel >= 3
                ),
            )
        }
    }

    fun getMovieGameLevelUISate(userEntity: UserEntity): List<ItemGameLevelUIState> {
        return userEntity.run {
            listOf(
                ItemGameLevelUIState(
                    level = 1,
                    questionsCount = if (moviesGameLevel > 1) 5 else numMoviesQuestionsPassed,
                    max = 5,
                    isOpenLevel = moviesGameLevel >= 1
                ),
                ItemGameLevelUIState(
                    level = 2,
                    questionsCount = if (moviesGameLevel > 2) 10 else numMoviesQuestionsPassed,
                    max = 10,
                    isOpenLevel = moviesGameLevel >= 2
                ),
                ItemGameLevelUIState(
                    level = 3,
                    questionsCount = if (moviesGameLevel > 3) 15 else numMoviesQuestionsPassed,
                    max = 15,
                    isOpenLevel = moviesGameLevel >= 3
                ),
            )
        }
    }

    fun getTvGameLevelUISate(userEntity: UserEntity): List<ItemGameLevelUIState> {
        return userEntity.run {
            listOf(
                ItemGameLevelUIState(
                    level = 1,
                    questionsCount = if (tvShowGameLevel > 1) 5 else numTvShowQuestionsPassed,
                    max = 5,
                    isOpenLevel = tvShowGameLevel >= 1
                ),
                ItemGameLevelUIState(
                    level = 2,
                    questionsCount = if (tvShowGameLevel > 2) 10 else numTvShowQuestionsPassed,
                    max = 10,
                    isOpenLevel = tvShowGameLevel >= 2
                ),
                ItemGameLevelUIState(
                    level = 3,
                    questionsCount = if (tvShowGameLevel > 3) 15 else numTvShowQuestionsPassed,
                    max = 15,
                    isOpenLevel = tvShowGameLevel >= 3
                ),
            )
        }
    }

    fun getMemorizeGameLevelUISate(userEntity: UserEntity): List<ItemGameLevelUIState> {
        return userEntity.run {
            listOf(
                ItemGameLevelUIState(
                    level = 1,
                    questionsCount = 0,
                    hasProgress = false,
                    max = 0,
                    isOpenLevel = memorizeGameLevel >= 1,
                ) { memorizeGameLevel > it },
                ItemGameLevelUIState(
                    level = 2,
                    questionsCount = 0,
                    hasProgress = false,
                    max = 0,
                    isOpenLevel = memorizeGameLevel >= 2
                ) { memorizeGameLevel > it },
                ItemGameLevelUIState(
                    level = 3,
                    questionsCount = 0,
                    hasProgress = false,
                    max = 0,
                    isOpenLevel = memorizeGameLevel >= 3
                ) { memorizeGameLevel > it },
            )
        }
    }
}