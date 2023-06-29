package com.chocolatecake.ui.sound_when_play

import android.content.Context
import android.media.MediaPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SoundManager @Inject constructor(@ApplicationContext private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null
    var isSoundOn: Boolean = false

    fun playSound(soundResourceId: Int) {
        if(isSoundOn){
            return
        }
        mediaPlayer = MediaPlayer.create(context, soundResourceId)
        mediaPlayer?.start()
        isSoundOn = true
    }

    fun stopSound() {
        isSoundOn = false
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun toggleSound(soundResourceId: Int) {
        isSoundOn = !isSoundOn
        if (!isSoundOn) {
            stopSound()
        } else {
            stopSound()
            playSound(soundResourceId)
        }
    }
}
