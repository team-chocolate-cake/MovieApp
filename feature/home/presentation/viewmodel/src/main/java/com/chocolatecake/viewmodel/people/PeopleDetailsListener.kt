package com.chocolatecake.viewmodel.people

import com.chocolatecake.bases.BaseInteractionListener

interface PeopleDetailsListener : BaseInteractionListener {
    fun onClickMedia(itemId: Int,name:String)
    fun backNavigate()

}