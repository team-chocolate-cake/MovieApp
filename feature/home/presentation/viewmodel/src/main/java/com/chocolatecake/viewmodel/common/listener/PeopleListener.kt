package com.chocolatecake.viewmodel.common.listener

import com.chocolatecake.bases.BaseInteractionListener

interface PeopleListener: BaseInteractionListener {
    fun onClickPeople(id: Int)
}