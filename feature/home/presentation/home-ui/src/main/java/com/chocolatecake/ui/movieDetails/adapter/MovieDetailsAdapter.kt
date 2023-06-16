package com.chocolatecake.ui.movieDetails.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.adapter.HomeAdapter
import com.chocolatecake.ui.home.adapter.NowPlayingAdapter
import com.chocolatecake.ui.home.adapter.PopularMoviesAdapter
import com.chocolatecake.ui.home.adapter.PopularPeopleAdapter
import com.chocolatecake.ui.home.adapter.TopRatedAdapter
import com.chocolatecake.ui.home.adapter.TrendingAdapter
import com.chocolatecake.ui.home.adapter.UpComingAdapter
import com.chocolatecake.ui.home.databinding.HomeRecyclerviewNowPlayingBinding
import com.chocolatecake.ui.home.databinding.HomeRecyclerviewSliderBinding
import com.chocolatecake.ui.home.databinding.HomeRecyclerviewTrendingBinding
import com.chocolatecake.ui.home.databinding.MovieDetailsItemPopularPeopleBinding
import com.chocolatecake.ui.home.databinding.MovieDetailsItemRecommendedBinding
import com.chocolatecake.ui.home.databinding.MovieDetailsItemReviewsBinding
import com.chocolatecake.viewmodel.home.HomeItem
import com.chocolatecake.viewmodel.home.HomeListener
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsItem
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsType
import com.chocolatecake.viewmodel.movieDetails.ui_state.MovieDetailsListener

class MovieDetailsAdapter(
    private var itemsMovie: MutableList<MovieDetailsItem>,
    private val listener: MovieDetailsListener,
) : BaseAdapter<MovieDetailsItem>(itemsMovie, listener) {
    override val layoutID: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            MovieDetailsType.PEOPLE.ordinal -> {
                PeopleViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.movie_details_item_popular_people, parent, false
                    )
                )
            }

            MovieDetailsType.RECOMMENDED.ordinal -> {
                RecommendedViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.movie_details_item_recommended, parent, false
                    )
                )
            }

            MovieDetailsType.REVIEWS.ordinal -> {
                ReviewsViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.movie_details_item_reviews, parent, false
                    )
                )
            }


            else -> throw Exception("Mimo")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is PeopleViewHolder -> bindPeople(holder, position)
            is RecommendedViewHolder -> bindRecommended(holder, position)
            is ReviewsViewHolder -> bindReviews(holder, position)



        }
    }

    fun setItem(item: MovieDetailsItem) {
        val newItems = itemsMovie.apply {
            removeAt(item.type.ordinal)
            add(item.type.ordinal, item)
        }
        setItems(newItems)
    }

    override fun setItems(newItems: List<MovieDetailsItem>) {
        itemsMovie = newItems.sortedBy { it.type.ordinal }.toMutableList()
        super.setItems(newItems)
    }


    private fun bindPeople(holder: PeopleViewHolder, position: Int) {
        val people = itemsMovie[position] as MovieDetailsItem.People
        val adapter = PeopleAdapter(people.list!! ,listener)
        holder.binding.recyclerViewPeople.adapter = adapter
        holder.binding.item = people
    }

    private fun bindRecommended(holder: RecommendedViewHolder, position: Int) {
        val recommended = itemsMovie[position] as MovieDetailsItem.Recommended
        val adapter = RecommendedMoviesAdapter(recommended.list!!, listener)
        holder.binding.recyclerViewRecommened.adapter = adapter
        holder.binding.item = recommended
    }

    private fun bindReviews(holder: ReviewsViewHolder, position: Int) {
        val topRated = itemsMovie[position] as MovieDetailsItem.Reviews
        val adapter = ReviewsAdapter(topRated.list!!, listener)
        holder.binding.recyclerViewReviews.adapter = adapter
        holder.binding.item = topRated
    }


    override fun getItemViewType(position: Int): Int = itemsMovie[position].type.ordinal

    class PeopleViewHolder(val binding: MovieDetailsItemPopularPeopleBinding) :
        BaseViewHolder(binding)

    class RecommendedViewHolder(val binding: MovieDetailsItemRecommendedBinding) :
        BaseViewHolder(binding)

    class ReviewsViewHolder(val binding: MovieDetailsItemReviewsBinding) : BaseViewHolder(binding)


}