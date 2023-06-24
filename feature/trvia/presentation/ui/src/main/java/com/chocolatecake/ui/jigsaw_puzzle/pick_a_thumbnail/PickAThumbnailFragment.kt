package com.chocolatecake.ui.jigsaw_puzzle.pick_a_thumbnail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentPickAThumbnailBinding
import com.chocolatecake.viewmodel.jigsaw_puzzle.compose_image.PickATThumbnailUiEvent
import com.chocolatecake.viewmodel.jigsaw_puzzle.compose_image.PickAThumbnailUiState
import com.chocolatecake.viewmodel.jigsaw_puzzle.compose_image.PickAThumbnailViewModel
import java.io.IOException

class PickAThumbnailFragment :
    BaseFragment<FragmentPickAThumbnailBinding, PickAThumbnailUiState, PickATThumbnailUiEvent>() {


    var mCurrentPhotoPath: String? = null


    override val layoutIdFragment = R.layout.fragment_pick_a_thumbnail
    override val viewModel by viewModels<PickAThumbnailViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {

        } catch (e: IOException) {
            Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }


    override fun onEvent(event: PickATThumbnailUiEvent) {

    }


    private companion object {
        const val REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE = 2
        const val REQUEST_IMAGE_CAPTURE = 1
        const val REQUEST_PERMISSION_READ_EXTERNAL_STORAGE = 3
        const val REQUEST_IMAGE_GALLERY = 4
    }

}