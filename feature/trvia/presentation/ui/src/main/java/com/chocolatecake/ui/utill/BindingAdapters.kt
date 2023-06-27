package com.chocolatecake.ui.utill

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.viewmodel.common.AnswerListener
import com.chocolatecake.viewmodel.common.model.GameUiState
import com.github.guilhe.views.CircularProgressView

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

@BindingAdapter("app:progressColors")
fun CircularProgressView.setProgressAnimated(progress: Int?) {
    if (progress == null) return
    val colorRes = if (progress < max / 2) {
        R.array.error_array_colors
    } else {
        R.array.correct_array_colors
    }
    val colors = context.resources.getIntArray(colorRes)
    val positions = FloatArray(colors.size) { index -> index.toFloat() / (colors.size - 1) }
    setProgressColors(colors, positions, true)
}


@BindingAdapter("app:hearCount")
fun LinearLayout.createHearts(heartCount: Int) {
    this.removeAllViews()
    val inflater = LayoutInflater.from(context)
    repeat(heartCount) {
        addView(inflater.inflate(R.layout.item_heart, this, false))
    }
}

@BindingAdapter(value = ["app:isVisibleAnimated"])
fun View.isVisibleAnimated(isVisible: Boolean) {
    animate().apply {
        val newAlpha = if (isVisible) 1f else 0f
        scaleX(-scaleX)
        alpha(newAlpha)
        duration = 300
        interpolator = AccelerateDecelerateInterpolator() // Animation interpolator
        withEndAction { this@isVisibleAnimated.alpha = newAlpha }
    }.start()
}

@BindingAdapter(value = ["app:listener", "app:answer", "app:userAnswer"])
fun TextView.setAnswerCardColor(
    listener: AnswerListener,
    answer: GameUiState.AnswerUiState?,
    userAnswer: Int?,
) {
    background = if (userAnswer != null && userAnswer == answer?.position) {
        if (answer.isCorrect){
            context.getDrawable(R.drawable.correct_answer_background)
        }else{
            context.getDrawable(R.drawable.wrong_answer_background)
        }
    }else{
        context.getDrawable(R.drawable.textfield_background)
    }
    setOnClickListener {
        listener.onClickAnswer(answer?.position ?: 0)
    }
}