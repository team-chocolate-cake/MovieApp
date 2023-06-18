package com.chocolatecake.viewmodel.common.listener

import com.chocolatecake.bases.BaseInteractionListener

interface SeasonListener: BaseInteractionListener {
    fun onClickSeason(id: Int)
}