package com.chocolatecake.ui.eposideDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.chocolatecake.ui.home.BR
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.ItemEpisodeDetailsRateBottomSheetBinding
import com.chocolatecake.viewmodel.eposideDetails.EpisodeDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeRateBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: ItemEpisodeDetailsRateBottomSheetBinding
    val viewModel by activityViewModels<EpisodeDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_episode_details_rate_bottom_sheet,
                container,
                false
            )
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.buttonApply.setOnClickListener{
            viewModel.submitRating()
            dismiss()
        }
    }
}