package com.chocolatecake.movieapp.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.ui.home.ui_state.UpComingMoviesUiState
import com.smarteist.autoimageslider.SliderViewAdapter

class UpComingAdapter(private val imageList: List<UpComingMoviesUiState>) :
    SliderViewAdapter<UpComingAdapter.ImageSliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?): ImageSliderViewHolder {
        val view =
            LayoutInflater.from(parent?.context).inflate(R.layout.home_item_image_slider, parent, false)
        return ImageSliderViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ImageSliderViewHolder?, position: Int) {
        viewHolder?.bind(imageList[position])
    }

    override fun getCount(): Int {
        return imageList.size
    }

    inner class ImageSliderViewHolder(itemView: View) : ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.image_view)

        fun bind(imageResId: UpComingMoviesUiState, position: Int) {
            imageView.setImageResource(imageList[position])
        }
    }
}
