package com.chocolatecake.viewmodel.tv_details.listener

import com.chocolatecake.bases.BaseInteractionListener

interface SeasonListener : BaseInteractionListener {
    fun onClickSeason(seasonNumber: Int)
}