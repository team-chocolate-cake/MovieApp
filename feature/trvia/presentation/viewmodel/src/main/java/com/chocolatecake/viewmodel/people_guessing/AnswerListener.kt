package com.chocolatecake.viewmodel.people_guessing

import com.chocolatecake.bases.BaseInteractionListener

interface AnswerListener: BaseInteractionListener {
    fun onClickAnswer(position: Int)
}