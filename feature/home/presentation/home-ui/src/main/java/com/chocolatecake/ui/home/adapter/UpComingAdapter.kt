package com.chocolatecake.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.HomeItemImageSliderBinding
import com.chocolatecake.viewmodel.home.UpComingMoviesUiState
import com.smarteist.autoimageslider.SliderViewAdapter

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

    inner class ImageSliderViewHolder(val binding: HomeItemImageSliderBinding) : ViewHolder(binding.root) {
        fun bind(itemUpComingMovie: UpComingMoviesUiState) {
            binding.item = itemUpComingMovie
        }
    }
}
