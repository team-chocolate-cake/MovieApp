package com.chocolatecake.ui.youtube_player

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

    override fun onEvent(event: TrailerInteraction) {

    }
}