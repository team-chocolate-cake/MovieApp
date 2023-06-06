package com.chocolatecake.movieapp.ui.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.ui.base.BaseAdapter

@BindingAdapter(value = ["app:imageUrlWithUrl"])
fun ImageView.loadImageWithUrl(url: String?) {
    Glide.with(context)
        .load(url)
        .fitCenter()
        .centerCrop()
        .into(this)
}

@BindingAdapter(value = ["app:items"])
fun <T> RecyclerView.setRecyclerItems(items: List<T>?) {
    (adapter as BaseAdapter<T>).setItems(items ?: emptyList())
}
@BindingAdapter(value = ["app:isVisible"])
fun View.isVisible(isVisible: Boolean?) {
    if (isVisible == true) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}