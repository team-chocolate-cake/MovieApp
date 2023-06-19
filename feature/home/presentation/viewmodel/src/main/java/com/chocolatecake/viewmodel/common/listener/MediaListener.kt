package com.chocolatecake.viewmodel.common.listener

import com.chocolatecake.bases.BaseInteractionListener

interface MediaListener: BaseInteractionListener {
    fun onClickMedia(id: Int)
}