package com.chocolatecake.movieapp.ui

import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("app:setTipError")
fun EditText.setTipError(errorMessage: Int?) {
    if (errorMessage == null) return
    else error = context.getString(errorMessage)
}

@BindingAdapter("app:isVisible")
fun View.isVisible(isVisible: Boolean?) {
    if (isVisible == null) return
    visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}