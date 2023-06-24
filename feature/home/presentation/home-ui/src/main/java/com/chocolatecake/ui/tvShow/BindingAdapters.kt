package com.chocolatecake.ui.tvShow

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.tv_shows.TVShowsType
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter(value = ["app:selectedTVShowType"])
fun ChipGroup.setSelectedTVShowsChip(type: TVShowsType) {
    when (type) {
        TVShowsType.ON_THE_AIR -> check(R.id.chip_on_the_air)
        TVShowsType.AIRING_TODAY -> check(R.id.chip_airing_today)
        TVShowsType.TOP_RATED -> check(R.id.chip_top_rated)
        TVShowsType.POPULAR -> check(R.id.chip_popular)
    }
}

@BindingAdapter("scrollToTop")
fun setScrollToTopListener(fab: FloatingActionButton, recyclerView: RecyclerView) {
    fab.setOnClickListener {
        recyclerView.smoothScrollToPosition(0)
    }
}

@BindingAdapter("showOnScroll")
fun setShowOnScrollListener(fab: FloatingActionButton, recyclerView: RecyclerView) {
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy > 0 && fab.visibility == View.GONE) {
                fab.show()
            } else if (dy < 0 && fab.visibility == View.VISIBLE) {
                fab.hide()
            }
        }
    })
}