package com.chocolatecake.ui.movieDetails

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.avatarfirst.avatargenlib.AvatarConstants
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup

@BindingAdapter(value = ["app:imageUrlByBackDropPath"])
fun ImageView.loadImageByBackDropPath(backDropPath: String?) {
    //TODO Remove hardcoded link
    if (!backDropPath.isNullOrEmpty())
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500"+backDropPath)
            .fitCenter()
            .centerCrop()
            .into(this)
}

@BindingAdapter(value = ["app:Genres"])
fun ChipGroup.setGenresMovieDetails(
    items: List<String>?,
) {
    this.removeAllViews()
    items?.forEach { genre ->
        var chip = Chip(this.context)
        chip.setChipBackgroundColorResource(com.chocolatecake.bases.R.color.on_background_38);
        chip.setCloseIconVisible(true)
        chip.setTextColor(getResources().getColor(com.chocolatecake.bases.R.color.on_background_38))
        chip.setText(genre)
        chip.ensureAccessibleTouchTarget(0)
        val chipDrawable = ChipDrawable.createFromAttributes(
            this.context,
            null,
            0,
            com.chocolatecake.bases.R.style.MovieDetailsChipStyle
        )
        chip.setChipDrawable(chipDrawable)
        chip.isEnabled = false
        this.addView(chip)
    }
}

@BindingAdapter(value = ["app:imageUrlForReviews" ,"app:autherName"])
fun ImageView.loadImageForReviews(backDropPath: String? , autherName:String) {
    //TODO Remove hardcoded link
    if (!backDropPath.isNullOrEmpty())
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500"+backDropPath)
            .fitCenter()
            .centerCrop()
            .placeholder(generateImage(this.context , autherName))
            .into(this)
}

fun generateImage(context:Context , name:String): BitmapDrawable {
    return AvatarGenerator.AvatarBuilder(context)
        .setLabel(name)
        .setAvatarSize(120)
        .setTextSize(30)
        .toSquare()
        .toCircle()
        .build()
}