package com.chocolatecake.movieapp.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.databinding.HomeRecyclerviewNowPlayingBinding
import com.chocolatecake.movieapp.databinding.HomeRecyclerviewPopularMoviesBinding
import com.chocolatecake.movieapp.databinding.HomeRecyclerviewPopularPeopleBinding
import com.chocolatecake.movieapp.databinding.HomeRecyclerviewRecommendedBinding
import com.chocolatecake.movieapp.databinding.HomeRecyclerviewSliderBinding
import com.chocolatecake.movieapp.databinding.HomeRecyclerviewTopRatedBinding
import com.chocolatecake.movieapp.databinding.HomeRecyclerviewTrendingBinding
import com.chocolatecake.movieapp.ui.base.BaseAdapter
import com.chocolatecake.movieapp.ui.home.HomeItem
import com.chocolatecake.movieapp.ui.home.adapter.NowPlayingAdapter
import com.chocolatecake.movieapp.ui.home.adapter.PopularMoviesAdapter
import com.chocolatecake.movieapp.ui.home.adapter.PopularPeopleAdapter
import com.chocolatecake.movieapp.ui.home.adapter.RecommendedAdapter
import com.chocolatecake.movieapp.ui.home.adapter.TopRatedAdapter
import com.chocolatecake.movieapp.ui.home.adapter.TrendingAdapter

class HomeAdapter(
    private var itemsHome: MutableList<HomeItem>,
    private val listener: HomeListener
) :
    BaseAdapter<HomeItem>(itemsHome, listener) {

    override val layoutID: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            HomeItem.HomeItemType.SLIDER.ordinal -> {
                SliderViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_item_image_slider, parent, false
                    )
                )
            }

            HomeItem.HomeItemType.NOW_PLAYING.ordinal -> {
                NowPlayingViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_item_now_playing, parent, false
                    )
                )
            }

            HomeItem.HomeItemType.TRENDING.ordinal -> {
                TrendingViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_item_trending, parent, false
                    )
                )
            }

            HomeItem.HomeItemType.TOP_RATED.ordinal -> {
                TopRatedViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_item_top_rated, parent, false
                    )
                )
            }

            HomeItem.HomeItemType.POPULAR_PEOPLE.ordinal -> {
                TopRatedViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_item_popular_people, parent, false
                    )
                )
            }

            HomeItem.HomeItemType.POPULAR_MOVIES.ordinal -> {
                TopRatedViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_item_popular_movies, parent, false
                    )
                )
            }

            HomeItem.HomeItemType.RECOMMENDED.ordinal -> {
                TopRatedViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.home_item_recommended, parent, false
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
            is RecommendedViewHolder -> bindRecommended(holder, position)

        }
    }

    private fun bindSlider(holder: SliderViewHolder, position: Int) {
        val slider = itemsHome[position] as HomeItem.Slider
        holder.binding.item = slider
    }

    private fun bindNowPlaying(holder: NowPlayingViewHolder, position: Int) {
        val nowPlaying = itemsHome[position] as HomeItem.NowPlaying
        val adapter = NowPlayingAdapter(nowPlaying.list,R.layout.home_recyclerview_now_playing, listener)
        holder.binding.rvNowPlaying.adapter = adapter
        holder.binding.item = nowPlaying
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

    private fun bindTopRated(holder: TopRatedViewHolder, position: Int) {
        val topRated = itemsHome[position] as HomeItem.TopRated
        val adapter = TopRatedAdapter(topRated.list,R.layout.home_recyclerview_top_rated, listener)
        holder.binding.rvTopRated.adapter = adapter
        holder.binding.item = topRated
    }

    private fun bindTrending(holder: TrendingViewHolder, position: Int) {
        val trending = itemsHome[position] as HomeItem.Trending
        val adapter = TrendingAdapter(trending.list,R.layout.home_recyclerview_trending, listener)
        holder.binding.rvTrending.adapter = adapter
        holder.binding.item = trending
    }

    private fun bindPopularPeople(holder: PopularPeopleViewHolder, position: Int) {
        val popularPeople = itemsHome[position] as HomeItem.PopularPeople
        val adapter = PopularPeopleAdapter(popularPeople.list,R.layout.home_recyclerview_popular_people, listener)
        holder.binding.rvPopularPeople.adapter = adapter
        holder.binding.item = popularPeople
    }

    private fun bindPopularMovies(holder: PopularMoviesViewHolder, position: Int) {
        val popularMovies = itemsHome[position] as HomeItem.PopularMovies
        val adapter = PopularMoviesAdapter(popularMovies.list,R.layout.home_recyclerview_popular_movies, listener)
        holder.binding.rvPopular.adapter = adapter
        holder.binding.item = popularMovies
    }

    private fun bindRecommended(holder: RecommendedViewHolder, position: Int) {
        val recommendedMovies = itemsHome[position] as HomeItem.RecommendedMovies
        val adapter = RecommendedAdapter(recommendedMovies.list,R.layout.home_recyclerview_recommended, listener)
        holder.binding.rvRecommended.adapter = adapter
        holder.binding.item = recommendedMovies
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

    class RecommendedViewHolder(val binding: HomeRecyclerviewRecommendedBinding) :
        BaseViewHolder(binding)
}