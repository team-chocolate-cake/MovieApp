package com.chocolatecake.movieapp.ui.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.chocolatecake.movieapp.R

@BindingAdapter(value = ["app:imageUrlWithUrl"])
fun ImageView.loadImageWithUrl(url: String?) {
    Glide.with(context)
        .load(url)
        .fitCenter()
        .centerCrop()
        .into(this)
}