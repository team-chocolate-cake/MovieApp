package com.chocolatecake.viewmodel.common

import com.chocolatecake.bases.BaseInteractionListener

interface QuestionListener: BaseInteractionListener {
    fun onClickQuestion(questionChosenPosition: Int)
}