package com.chocolatecake.ui.utill

import android.content.res.ColorStateList
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.chocolatecake.ui.trivia.R
import com.github.guilhe.views.CircularProgressView

@BindingAdapter(value = ["app:timerColor", "app:maxTime"])
fun TextView.setTimerColor(time: Int?, max: Int?) {
    if (time != null && max != null) {
        val colorRes = if (time < max / 2) {
            R.color.error
        } else {
            R.color.correct
        }
        val color = ContextCompat.getColor(context, colorRes)
        val colorStateList = ColorStateList.valueOf(color)
        setTextColor(colorStateList)
    }
}

@BindingAdapter("app:progressColors")
fun CircularProgressView.setProgressAnimated(progress: Int) {
    val colorRes = if (progress < max / 2) {
        R.array.error_array_colors
    } else {
        R.array.correct_array_colors
    }
    val colors = context.resources.getIntArray(colorRes)
    setProgressColors(colors, floatArrayOf(), true)
}

