package com.chocolatecake.util

import android.annotation.SuppressLint
import android.app.UiModeManager
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.bases.R
import com.google.android.material.progressindicator.LinearProgressIndicator

@BindingAdapter(value = ["app:items"])
fun <T> RecyclerView.setRecyclerItems(items: List<T>?) {
    (adapter as BaseAdapter<T>).setItems(items ?: emptyList())
    smoothScrollToPosition(0)
}

@BindingAdapter(value = ["app:isVisible"])
fun View.isVisible(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}


@BindingAdapter(value = ["app:isVisibleOrGone"])
fun View.isVisibleOrGone(isVisible: Boolean?) {
    if (isVisible == true) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:hideWhenNotLoggedIn"])
fun View.hideWhenNotLoggedIn(hideWhenNotLoggedIn: Boolean?) {
    if (hideWhenNotLoggedIn == false) {
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

/// region image glide
@BindingAdapter(value = ["app:imageUrl"])
fun ImageView.loadImage(imageUrl: String?) {
    val imageLink = if (imageUrl == null || imageUrl.contains("null"))
        "https://pbs.twimg.com/profile_images/1243623122089041920/gVZIvphd_400x400.jpg" else imageUrl

    Glide.with(context)
        .load(imageLink)
        .thumbnail(Glide.with(context).load(R.raw.dots_loading))
        .centerCrop()
        .error(android.R.drawable.stat_notify_error)
        .into(this)
}

@SuppressLint("ResourceAsColor")
@BindingAdapter(value = ["app:imageUri", "app:imagePlaceholderColor"], requireAll = false)
fun ImageView.loadImageWithPlaceholderColor(imageUri: String?, imagePlaceholderColor: String?) {
    if (imageUri != null) {
        Glide.with(context)
            .load(imageUri)
            .into(this)
    } else {
        this?.let {
            this.setBackgroundColor(R.color.background)
        }
    }
}

@BindingAdapter(value = ["app:profileUrl"])
fun ImageView.loadProfileImage(profileUrl: String?) {
    val imageLink = if (profileUrl == null || profileUrl.contains("null"))
        "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png" else profileUrl

    Glide.with(context)
        .load(imageLink)
        .thumbnail(Glide.with(context).load(R.raw.dots_loading))
        .centerCrop()
        .error(android.R.drawable.stat_notify_error)
        .into(this)
}
/// endregion

@BindingAdapter(value = ["app:hideResult", "app:query"])
fun <T> View.hideResult(list: List<T>?, text: String) {
    if (list.isNullOrEmpty() && text.isNotBlank()) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:hideWhenNoList"])
fun <T> View.hideWhenNoList(list: List<T>?) {
    if (list.isNullOrEmpty()) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
    }
}

@BindingAdapter(value = ["app:hideWhenNoResult"])
fun <T> View.hideWhenNoResult(list: List<T>?) {
    if (list.isNullOrEmpty()) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
    }
}

@BindingAdapter(value = ["app:hideWhenEmpty"])
fun <T> View.hideWhenEmpty(list: List<T>?) {
    if (list.isNullOrEmpty()) {
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
fun View.showWhenEmptyData(query: String?) {
    if (query?.isEmpty() == true) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenNoResult"])
fun <T> View.showWhenNoResult(list: List<T>?) {
    if (list.isNullOrEmpty()) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}


@BindingAdapter("app:showWhenError")
fun <T> View.showWhenError(list: List<T>?) {
    if (list?.isEmpty() == true) {
        this.visibility = View.GONE

    } else {
        this.visibility = View.VISIBLE
    }
}

@BindingAdapter(value = ["app:showWhenQueryEmpty", "app:showWhenFailure"])
fun View.showBasedOnState(
    query: String?,
    error: List<String>?
) {
    visibility = when {
        query.isNullOrEmpty() && error.isNullOrEmpty() -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenFailure"])
fun View.showBasedOnState(
    error: List<String>?
) {
    visibility = when {
        error?.isNotEmpty() == true -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("app:toggleUiMode")
fun SwitchCompat.toggleUiMode(uiModeManager: UiModeManager) {
    this.setOnCheckedChangeListener { _, isChecked ->
        if (isChecked) {
            uiModeManager.nightMode = UiModeManager.MODE_NIGHT_NO
        } else {
            uiModeManager.nightMode = UiModeManager.MODE_NIGHT_YES
        }
    }
}

@BindingAdapter("app:hideWhenError")
fun <T> View.hideWhenError(list: List<T>?) {
    if (list?.isEmpty() == true) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
    }
}

@BindingAdapter("app:hideWhenRefresh")
fun <T> View.hideWhenRefresh(isRefresh: Boolean?) = if (isRefresh == true) {
    this.visibility = View.GONE
} else {
    this.visibility = View.VISIBLE
}

@BindingAdapter("app:hideWhenLoading")
fun <T> View.hideWhenLoading(isLoading: Boolean?) {
    if (isLoading == true) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
    }
}

@BindingAdapter("app:onClickNavigation")
fun androidx.appcompat.widget.Toolbar.addNavigationListener(onClick: () -> Unit) {
    this.setNavigationOnClickListener {
        onClick()

    }
}

    @BindingAdapter("convertGenderText")
    fun TextView.convertGenderText(gender: String?) {
        text = when (gender) {
            "1" -> context.getString(R.string.female)
            "2" -> context.getString(R.string.male)
            else -> ""
        }.takeIf { gender != null } ?: ""

    }