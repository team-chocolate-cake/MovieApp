package com.chocolatecake.viewmodel.my_list_details

import com.chocolatecake.bases.BaseInteractionListener

interface MyListDetailsListener : BaseInteractionListener {
    fun onClickItem(itemId: Int)

    fun onClickBackButton()
}