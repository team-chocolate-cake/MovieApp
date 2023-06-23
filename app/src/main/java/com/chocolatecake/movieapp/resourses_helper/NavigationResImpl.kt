package com.chocolatecake.movieapp.resourses_helper

import com.chocolatecake.bases.NavigationRes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationResImpl @Inject constructor() : NavigationRes {
    override val homeFeatureLink = com.chocolatecake.ui.home.R.id.homeFragment
    override val gameFeatureLink = com.chocolatecake.ui.trivia.R.id.game_nav_graph
    override val authFeatureLink = com.chocolatecake.ui.home.R.id.loginFragment
}