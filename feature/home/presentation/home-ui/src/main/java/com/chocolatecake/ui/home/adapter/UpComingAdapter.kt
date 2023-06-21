package com.chocolatecake.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.HomeItemImageSliderBinding
import com.chocolatecake.viewmodel.home.UpComingMoviesUiState

class UpComingAdapter(
    private val upComingList: List<UpComingMoviesUiState>, private val viewPager: ViewPager2
) : RecyclerView.Adapter<UpComingAdapter.ImageSliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {
        return ImageSliderViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.home_item_image_slider,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return upComingList.size
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
        holder.bind(upComingList[position])
    }

    inner class ImageSliderViewHolder(val binding: HomeItemImageSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemUpComingMovie: UpComingMoviesUiState) {
            binding.item = itemUpComingMovie
        }
    }
}
