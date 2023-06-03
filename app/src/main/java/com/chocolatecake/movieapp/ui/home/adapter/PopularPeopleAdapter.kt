package com.chocolatecake.movieapp.ui.home.adapter

import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.data.local.database.entity.actor.PopularPeopleEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.RecommendedMovieEntity
import com.chocolatecake.movieapp.home.adapter.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseAdapter

class PopularPeopleAdapter (itemsPopularPeople:List<PopularPeopleEntity?>, listener: HomeListener):
    BaseAdapter<PopularPeopleEntity?>(itemsPopularPeople,listener) {
    override val layoutID: Int
        get()  = R.layout.home_item_popular_people

}