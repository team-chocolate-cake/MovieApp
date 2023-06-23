package com.chocolatecake.viewmodel.common.listener

import com.chocolatecake.bases.BaseInteractionListener

interface MovieListener: BaseInteractionListener {
    fun onClickMedia(id: Int)
}