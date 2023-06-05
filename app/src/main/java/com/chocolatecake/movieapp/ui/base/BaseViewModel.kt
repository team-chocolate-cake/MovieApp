package com.chocolatecake.movieapp.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    abstract fun getData()
}