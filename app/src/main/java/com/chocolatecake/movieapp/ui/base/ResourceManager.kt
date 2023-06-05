package com.chocolatecake.movieapp.ui.base

import android.content.res.Resources
import javax.inject.Inject

class ResourceManager @Inject constructor(private val resources: Resources) {
    fun getString(resourceId: Int): String {
        return resources.getString(resourceId)
    }
}