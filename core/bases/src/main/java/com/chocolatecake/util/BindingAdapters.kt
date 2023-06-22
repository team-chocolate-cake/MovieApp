package com.chocolatecake.util

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chocolatecake.bases.BaseAdapter
import com.google.android.material.progressindicator.LinearProgressIndicator

@BindingAdapter(value = ["app:items"])
fun <T> RecyclerView.setRecyclerItems(items: List<T>?) {
    (adapter as BaseAdapter<T>).setItems(items ?: emptyList())
}

@BindingAdapter(value = ["app:isVisible"])
fun View.isVisible(isVisible: Boolean?) {
    if (isVisible == true) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}

@BindingAdapter("app:setTipError")
fun EditText.setTipError(errorMessage: String?) {
    if (errorMessage == null) return
    else error = errorMessage
}

@BindingAdapter(value = ["app:imageUrl"])
fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(context)
        .load(imageUrl)
        .fitCenter()
        .centerCrop()
        .into(this)
}

@BindingAdapter(value = ["app:hideResult", "app:query"])
fun <T> View.hideResult(list: List<T>?, text: String) {
    if (list.isNullOrEmpty() && text.isNotBlank()) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:loading"])
fun LinearProgressIndicator.isLoading(isLoading: Boolean?) {
    if (isLoading == true) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenQueryEmpty"])
fun View.showWhenEmptyData(query: String?){
    if(query?.isEmpty() == true){
        this.visibility = View.VISIBLE
    }else{
        this.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenNoResult"])
fun <T> View.showWhenNoResult(list: List<T>?){
    if (list.isNullOrEmpty()){
        this.visibility = View.VISIBLE
    }else{
        this.visibility = View.GONE
    }
}

@BindingAdapter("app:showWhenError")
fun <T> View.showWhenError(list: List<T>?){
    if(list?.isNotEmpty() == true){
        this.visibility = View.VISIBLE
    }else{
        this.visibility = View.GONE
    }
}

@BindingAdapter("convertGenderText")
fun TextView.convertGenderText(gender: String?) {
    text = if (gender != null) {
        when (gender) {
            "1" -> "female"
            "2" -> "male"
            else -> ""
        }
    } else {
        ""
    }
}