package com.chocolatecake.movieapp.home.adapter

import com.chocolatecake.movieapp.ui.base.BaseInteractionListener

interface HomeListener :BaseInteractionListener{

    fun onClickNowPlaying(itemId:Int)
    fun onClickTrending(itemId:Int)
}