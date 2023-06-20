package com.chocolatecake.ui.tvShow

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.tv_shows.TVShowsType
import com.chocolatecake.viewmodel.tv_shows.TVShowsUI
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@BindingAdapter(value = ["app:selectedTVShowType"])
fun ChipGroup.setSelectedTVShowsChip(type: TVShowsType) {
    when (type) {
        TVShowsType.ON_THE_AIR -> check(R.id.chip_on_the_air)
        TVShowsType.AIRING_TODAY -> check(R.id.chip_airing_today)
        TVShowsType.TOP_RATED -> check(R.id.chip_top_rated)
        TVShowsType.POPULAR -> check(R.id.chip_popular)
    }
}
/*
@BindingAdapter("app:showWhenListEmpty")
fun showWhenListEmpty(view: View, flow: Flow<PagingData<TVShowsUI>>) {
    view.isVisible = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        flow.collectLatest { pagingData ->
            view.isVisible = pagingData.isEmpty()
        }
    }
}
*/