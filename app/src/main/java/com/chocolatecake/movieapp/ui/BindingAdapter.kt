package com.chocolatecake.movieapp.ui

import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("app:setTipError")
fun EditText.setTipError(errorMessage: String?) {
    if (errorMessage == null) return
    else this.error = errorMessage
}

@BindingAdapter("app:isVisible")
fun View.isVisible(isVisible: Boolean?) {
    if (isVisible == null) return
    visibility = if (isVisible == true) View.VISIBLE else View.INVISIBLE
}