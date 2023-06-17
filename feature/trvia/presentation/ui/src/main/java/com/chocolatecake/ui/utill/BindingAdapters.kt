package com.chocolatecake.ui.utill

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.chocolatecake.ui.trivia.R

@BindingAdapter(value = ["app:timerColor", "app:maxTime"])
fun TextView.setTimerColor(time: Int?, maxTime: Int?) {
    if (time != null && maxTime != null) {
        val colorRes = if (time < maxTime / 2) {
            R.color.error
        } else {
            R.color.correct
        }
        val color = ContextCompat.getColor(context, colorRes)
        val colorStateList = ColorStateList.valueOf(color)
        setTextColor(colorStateList)
    }
}

//@BindingAdapter("app:progressColors")
//fun CircularProgressView.setProgressAnimated(progress: Int) {
//    val colorRes = if (progress < max / 2) {
//        R.array.error_array_colors
//    } else {
//        R.array.correct_array_colors
//    }
//    val colors = context.resources.getIntArray(colorRes)
//    setProgressColors(colors, floatArrayOf(), true)
//}


@BindingAdapter("app:hearCount")
fun LinearLayout.createHearts(heartCount: Int) {
    this.removeAllViews()
    val inflater = LayoutInflater.from(context)
    repeat(heartCount) {
        addView(inflater.inflate(R.layout.item_heart, this, false))
    }
}
