package com.chocolatecake.viewmodel.myListDetails

import com.chocolatecake.bases.BaseInteractionListener

interface MyListDetailsListener : BaseInteractionListener {
    fun onClickItem(itemId: Int)

    fun onClickBackButton()
}