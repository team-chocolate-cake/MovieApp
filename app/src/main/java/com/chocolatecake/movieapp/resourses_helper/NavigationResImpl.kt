package com.chocolatecake.movieapp.resourses_helper

import com.chocolatecake.bases.NavigationRes
import com.chocolatecake.movieapp.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationResImpl @Inject constructor() : NavigationRes {
    override val homeFeature: Int = R.id.action_loginFragment_to_home_nav_graph
}