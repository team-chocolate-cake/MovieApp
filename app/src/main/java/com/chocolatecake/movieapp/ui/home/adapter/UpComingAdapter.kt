package com.chocolatecake.movieapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.smarteist.autoimageslider.SliderViewAdapter
import com.bumptech.glide.Glide
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.databinding.HomeItemImageSliderBinding
import com.chocolatecake.movieapp.ui.home.ui_state.UpComingMoviesUiState

class UpComingAdapter(
    private val upComingList: List<UpComingMoviesUiState>
) : SliderViewAdapter<UpComingAdapter.ImageSliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): ImageSliderViewHolder {
        return ImageSliderViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.home_item_image_slider,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: ImageSliderViewHolder?, position: Int) {
        viewHolder?.bind(upComingList[position])
    }

    override fun getCount(): Int {
        return upComingList.size
    }

    inner class ImageSliderViewHolder(val binding: HomeItemImageSliderBinding) :
        ViewHolder(binding.root) {
        fun bind(itemUpComingMovie: UpComingMoviesUiState) {
            binding.item = itemUpComingMovie
        }
    }
}
