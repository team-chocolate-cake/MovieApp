package com.chocolatecake.movieapp.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel(
    protected  val resourceManager: ResourceManager
) : ViewModel() {
    abstract fun getData()
}