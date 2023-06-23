package com.chocolatecake.ui.movieDetails

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.bumptech.glide.Glide
import com.chocolatecake.ui.home.databinding.GenereChipBinding
import com.chocolatecake.viewmodel.common.listener.ChipListener
import com.chocolatecake.viewmodel.common.model.UserListUi
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup

@BindingAdapter(value = ["app:Genres"])
fun ChipGroup.setGenresMovieDetails(
    items: List<String>?,
) {
    this.removeAllViews()
    items?.forEach { genre ->
        val chip = Chip(this.context)
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

@BindingAdapter(value = ["app:imageUrlForReviews", "app:autherName"])
fun ImageView.loadImageForReviews(backDropPath: String?, autherName: String) {
    //TODO Remove hardcoded link
    if (!backDropPath.isNullOrEmpty())
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500" + backDropPath)
            .fitCenter()
            .centerCrop()
            .placeholder(generateImage(this.context, autherName))
            .into(this)
}

fun generateImage(context: Context, name: String): BitmapDrawable {
    return AvatarGenerator.AvatarBuilder(context)
        .setLabel(name)
        .setAvatarSize(120)
        .setTextSize(30)
        .toSquare()
        .toCircle()
        .build()
}

@BindingAdapter(value = ["app:genreChips", "app:listener"])
fun ChipGroup.setGenreChips(
    chips: List<UserListUi>,
    chipListener: ChipListener
) {
    val inflater = LayoutInflater.from(context)
    for (chipUiState in chips) {
        val binding = DataBindingUtil.inflate<GenereChipBinding>(
            inflater,
            com.chocolatecake.ui.home.R.layout.genere_chip,
            this,
            false
        )
        binding.item = chipUiState
        binding.listener = chipListener
        addView(binding.root, 0)
    }
}

@BindingAdapter(value = ["app:emptyList"])
fun View.emptyList(list: List<Any>): Int {
    return if (list.isEmpty()) View.GONE else View.VISIBLE
}
@BindingAdapter(value = ["app:hideOnEmpty"])
fun View.hideOnEmpty(list: List<Any>) {
    if (list.isEmpty()) this.visibility = View.GONE else this.visibility = View.VISIBLE
}