package com.chocolatecake.ui.jigsaw_puzzle.compose_image

import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentComposePhotoBinding
import com.chocolatecake.viewmodel.jigsaw_puzzle.pick_a_thumbnail.ComposePhotoUiEvent
import com.chocolatecake.viewmodel.jigsaw_puzzle.pick_a_thumbnail.ComposePhotoUiState
import com.chocolatecake.viewmodel.jigsaw_puzzle.pick_a_thumbnail.ComposeTitleViewModel

class ComposePhotoFragment :BaseFragment<FragmentComposePhotoBinding,ComposePhotoUiState,ComposePhotoUiEvent>(){
    override val layoutIdFragment= R.layout.fragment_compose_photo
    override val viewModel by viewModels<ComposeTitleViewModel>()

    override fun onEvent(event: ComposePhotoUiEvent) {

    }

}