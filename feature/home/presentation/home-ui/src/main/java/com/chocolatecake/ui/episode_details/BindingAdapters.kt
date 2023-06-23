package com.chocolatecake.ui.episode_details

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@BindingAdapter("app:setVideoId")
fun setVideoId(view: YouTubePlayerView, videoId: String?) {
    view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            videoId?.let { youTubePlayer.cueVideo(it, 0f) }
        }
    })
}
@BindingAdapter(value = ["app:hideWhenNoProductionCode"])
fun <T> View.hideWhenNoProductionCode(productionCode:String){
    if (productionCode.isEmpty()){
        this.visibility = View.GONE
    }else{
        this.visibility = View.VISIBLE
    }
}
@BindingAdapter("textMaxChars")
fun TextView.setTextMaxChars(text: CharSequence?) {
    if (text != null && text.length > 10) {
        this.text = text.subSequence(0, 10)
    } else {
        this.text = text
    }
}
