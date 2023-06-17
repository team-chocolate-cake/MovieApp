package com.chocolatecake.viewmodel.common

import com.chocolatecake.bases.BaseInteractionListener

interface AnswerListener: BaseInteractionListener {
    fun onClickAnswer(position: Int)
}