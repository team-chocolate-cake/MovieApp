package com.chocolatecake.viewmodel.my_rated

import com.chocolatecake.bases.BaseInteractionListener

interface MyRatedListner :BaseInteractionListener{
    fun onBackPressed()
    fun onClickMovieChip()
    fun onClickTvShowChip()
}