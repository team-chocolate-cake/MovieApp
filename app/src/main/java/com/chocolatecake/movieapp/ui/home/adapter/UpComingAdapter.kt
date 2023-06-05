package com.chocolatecake.movieapp.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.smarteist.autoimageslider.SliderViewAdapter
import com.bumptech.glide.Glide
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.ui.home.ui_state.UpComingMoviesUiState

class UpComingAdapter(
    private val upComingList: List<UpComingMoviesUiState>,
    val layout: Int,
    listener: HomeListener
) :
    SliderViewAdapter<UpComingAdapter.ImageSliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?): ImageSliderViewHolder {
        val view = LayoutInflater.from(parent?.context)
            .inflate(layout, parent, false)
        return ImageSliderViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ImageSliderViewHolder?, position: Int) {
        viewHolder?.bind(upComingList[position])
    }

    override fun getCount(): Int {
        return upComingList.size
    }

    inner class ImageSliderViewHolder(itemView: View) : ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.image_view)

        fun bind(itemUpComingMovie: UpComingMoviesUiState) {
            Glide.with(itemView.context)
                .load(itemUpComingMovie.imageUrl)
                .into(imageView)
        }
    }
}
