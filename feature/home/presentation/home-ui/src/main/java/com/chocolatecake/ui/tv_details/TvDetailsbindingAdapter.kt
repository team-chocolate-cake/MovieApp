package com.chocolatecake.ui.tv_details

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.chocolatecake.bases.R
import com.chocolatecake.ui.home.databinding.GenereChipBinding
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiState
import com.chocolatecake.viewmodel.tv_details.listener.ChipListener
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
@BindingAdapter("app:chips")
fun ChipGroup.setChips(chips: List<String>) {
    removeAllViews()
    val inflater = LayoutInflater.from(context)
    for (chipText in chips) {
        val chip = inflater.inflate(com.chocolatecake.ui.home.R.layout.tv_details_item_chip, this, false) as Chip
        chip.apply {
            text = chipText
            isEnabled = false
        }
        addView(chip)
    }
}

@BindingAdapter(value = ["app:genreChips","app:listener"])
fun ChipGroup.setGenreChips(chips: List<TvDetailsUiState.UserListUi>, chipListener: ChipListener) {
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
@BindingAdapter(value=["app:emptyList"])
fun View.emptyList(list: List<Any>): Int {
    return if (list.isEmpty()) View.GONE else View.VISIBLE
}