package com.chocolatecake.viewmodel.youtube_trailer

data class YoutubePlayerUIState(
    val videoKey: String = "",
    val isLoading: Boolean = false,
    val errors: List<String>? = emptyList(),
) {
    val isError: Boolean
        get() = errors?.isNotEmpty() ?: false
}