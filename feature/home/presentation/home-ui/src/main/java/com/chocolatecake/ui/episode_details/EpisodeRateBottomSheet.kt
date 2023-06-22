package com.chocolatecake.ui.episode_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.chocolatecake.ui.home.BR
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.ItemEpisodeDetailsRateBottomSheetBinding
import com.chocolatecake.viewmodel.episode_details.EpisodeDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeRateBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: ItemEpisodeDetailsRateBottomSheetBinding
    private val viewModel by activityViewModels<EpisodeDetailsViewModel>()
    private var dismissListener: BottomSheetListener? = null
    private var userRating: Float = 0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
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

        binding.rating.setOnRatingBarChangeListener { _, rating, _ ->
            userRating = rating * 2
        }

        binding.buttonApply.setOnClickListener {
            dismissListener?.onApplyRateBottomSheet()
            dismissListener?.updateRatingValue(userRating)
            Toast.makeText(requireActivity(), userRating.toString(), Toast.LENGTH_SHORT).show()
            dismiss()
        }
        userRating = dismissListener?.retrieveRatingValue() ?: 0f
        binding.rating.rating = userRating / 2
    }

    interface BottomSheetListener {
        fun onApplyRateBottomSheet()
        fun updateRatingValue(rate: Float)
        fun retrieveRatingValue(): Float
    }
}
