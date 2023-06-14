package com.chocolatecake.viewmodel.common.listener

import com.chocolatecake.bases.BaseInteractionListener

interface MovieListener: BaseInteractionListener {
    fun onClickMovie(id: Int)
}