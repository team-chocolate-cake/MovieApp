package com.chocolatecake.ui.jigsaw_puzzle.compose_image

import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentComposePhotoBinding
import com.chocolatecake.viewmodel.jigsaw_puzzle.compose_photo.ComposePhotoUiEvent
import com.chocolatecake.viewmodel.jigsaw_puzzle.compose_photo.ComposePhotoUiState
import com.chocolatecake.viewmodel.jigsaw_puzzle.compose_photo.ComposePhotoViewModel

class ComposePhotoFragment :
    BaseFragment<FragmentComposePhotoBinding, ComposePhotoUiState, ComposePhotoUiEvent>() {
    override val layoutIdFragment = R.layout.fragment_compose_photo
    override val viewModel by viewModels<ComposePhotoViewModel>()


    override fun onEvent(event: ComposePhotoUiEvent) {
    }

}