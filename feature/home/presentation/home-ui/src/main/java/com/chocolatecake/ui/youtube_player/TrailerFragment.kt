package com.chocolatecake.ui.youtube_player

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentTrailerBinding
import com.chocolatecake.viewmodel.youtube_trailer.TrailerInteraction
import com.chocolatecake.viewmodel.youtube_trailer.TrailerViewModel
import com.chocolatecake.viewmodel.youtube_trailer.YoutubePlayerUIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrailerFragment :
    BaseFragment<FragmentTrailerBinding, YoutubePlayerUIState, TrailerInteraction>() {

    override val layoutIdFragment = R.layout.fragment_trailer
    override val viewModel: TrailerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        activity?.window?.navigationBarColor = Color.BLACK
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity?.window?.setDecorFitsSystemWindows(false)
        } else {
            activity?.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

    override fun onEvent(event: TrailerInteraction) {

    }
}