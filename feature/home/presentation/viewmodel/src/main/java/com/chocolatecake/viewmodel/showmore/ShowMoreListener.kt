package com.chocolatecake.viewmodel.showmore

import com.chocolatecake.bases.BaseInteractionListener

interface ShowMoreListener : BaseInteractionListener {
    fun onClickMedia(mediaId: Int)
    fun backNavigate()
}
