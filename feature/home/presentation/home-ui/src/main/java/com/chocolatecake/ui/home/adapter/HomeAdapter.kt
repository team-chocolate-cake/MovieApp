package com.chocolatecake.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.HomeRecyclerviewNowPlayingBinding
import com.chocolatecake.ui.home.databinding.HomeRecyclerviewPopularMoviesBinding
import com.chocolatecake.ui.home.databinding.HomeRecyclerviewPopularPeopleBinding
import com.chocolatecake.ui.home.databinding.HomeRecyclerviewSliderBinding
import com.chocolatecake.ui.home.databinding.HomeRecyclerviewTopRatedBinding
import com.chocolatecake.ui.home.databinding.HomeRecyclerviewTrendingBinding
import com.chocolatecake.viewmodel.home.HomeItem
import com.chocolatecake.viewmodel.home.HomeItemType
import com.chocolatecake.viewmodel.home.HomeListener

class HomeAdapter(
    private var itemsHome: MutableList<HomeItem>,
    private val listener: HomeListener
) : BaseAdapter<HomeItem>(itemsHome, listener) {

    override val layoutID: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            HomeItemType.SLIDER.ordinal -> {
                SliderViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_recyclerview_slider, parent, false
                    )
                )
            }

            HomeItemType.NOW_PLAYING.ordinal -> {
                NowPlayingViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_recyclerview_now_playing, parent, false
                    )
                )
            }

            HomeItemType.TRENDING.ordinal -> {
                TrendingViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_recyclerview_trending, parent, false
                    )
                )
            }

            HomeItemType.TOP_RATED.ordinal -> {
                TopRatedViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_recyclerview_top_rated, parent, false
                    )
                )
            }

            HomeItemType.POPULAR_PEOPLE.ordinal -> {
                PopularPeopleViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_recyclerview_popular_people, parent, false
                    )
                )
            }

            HomeItemType.POPULAR_MOVIES.ordinal -> {
                PopularMoviesViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_recyclerview_popular_movies, parent, false
                    )
                )
            }


            else -> throw Exception("Mimo")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is SliderViewHolder -> bindSlider(holder, position)
            is NowPlayingViewHolder -> bindNowPlaying(holder, position)
            is TrendingViewHolder -> bindTrending(holder, position)
            is TopRatedViewHolder -> bindTopRated(holder, position)
            is PopularPeopleViewHolder -> bindPopularPeople(holder, position)
            is PopularMoviesViewHolder -> bindPopularMovies(holder, position)


        }
    }

    fun setItem(item: HomeItem) {
        val newItems = itemsHome.apply {
            removeAt(item.type.ordinal)
            add(item.type.ordinal, item)
        }
        setItems(newItems)
    }

    override fun setItems(newItems: List<HomeItem>) {
        itemsHome = newItems.sortedBy { it.type.ordinal }.toMutableList()
        super.setItems(newItems)
    }

    private fun bindSlider(holder: SliderViewHolder, position: Int) {
        val upComing = itemsHome[position] as HomeItem.Slider
        val viewPager = holder.binding.viewPager
        val adapter = UpComingAdapter(upComing.list,listener)
        viewPager.adapter = adapter
        val dotsIndicator = holder.binding.dotsIndicator
        dotsIndicator.attachTo(viewPager)
        holder.binding.item = upComing
    }

    private fun bindNowPlaying(holder: NowPlayingViewHolder, position: Int) {
        val nowPlaying = itemsHome[position] as HomeItem.NowPlaying
        val adapter = NowPlayingAdapter(nowPlaying.list, listener)
        holder.binding.recyclerViewNowPlaying.adapter = adapter
        holder.binding.item = nowPlaying
    }

    private fun bindTopRated(holder: TopRatedViewHolder, position: Int) {
        val topRated = itemsHome[position] as HomeItem.TopRated
        val adapter = TopRatedAdapter(topRated.list, listener)
        holder.binding.recyclerViewTopRated.adapter = adapter
        holder.binding.item = topRated
    }

    private fun bindTrending(holder: TrendingViewHolder, position: Int) {

        val trending = itemsHome[position] as HomeItem.Trending
        val adapter = TrendingAdapter(trending.list, listener)
        holder.binding.recyclerViewTrending.adapter = adapter
        holder.binding.item = trending
        Log.i("trending", trending.toString())
    }

    private fun bindPopularPeople(holder: PopularPeopleViewHolder, position: Int) {
        val popularPeople = itemsHome[position] as HomeItem.PopularPeople
        val adapter = PopularPeopleAdapter(popularPeople.list, listener)
        holder.binding.recyclerViewPopularPeople.adapter = adapter
        holder.binding.item = popularPeople
    }

    private fun bindPopularMovies(holder: PopularMoviesViewHolder, position: Int) {
        val popularMovies = itemsHome[position] as HomeItem.PopularMovies
        val adapter = PopularMoviesAdapter(popularMovies.list, listener)
        holder.binding.recyclerViewPopularMovies.adapter = adapter
        holder.binding.item = popularMovies
    }


    override fun getItemViewType(position: Int): Int = itemsHome[position].type.ordinal

    class SliderViewHolder(val binding: HomeRecyclerviewSliderBinding) : BaseViewHolder(binding)
    class NowPlayingViewHolder(val binding: HomeRecyclerviewNowPlayingBinding) :
        BaseViewHolder(binding)

    class TrendingViewHolder(val binding: HomeRecyclerviewTrendingBinding) : BaseViewHolder(binding)
    class TopRatedViewHolder(val binding: HomeRecyclerviewTopRatedBinding) : BaseViewHolder(binding)
    class PopularPeopleViewHolder(val binding: HomeRecyclerviewPopularPeopleBinding) :
        BaseViewHolder(binding)

    class PopularMoviesViewHolder(val binding: HomeRecyclerviewPopularMoviesBinding) :
        BaseViewHolder(binding)


}