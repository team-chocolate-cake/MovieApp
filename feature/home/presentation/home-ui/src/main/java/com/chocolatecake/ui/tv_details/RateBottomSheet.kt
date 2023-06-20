package com.chocolatecake.ui.tv_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.chocolatecake.ui.home.BR
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.TvDetailsItemBotomSheetBinding
import com.chocolatecake.viewmodel.tv_details.TvDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RateBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: TvDetailsItemBotomSheetBinding
    val viewModel by activityViewModels<TvDetailsViewModel>()
    private var dismissListener: BottomSheetDismissListener? = null


    fun setListener(dismissListener: BottomSheetDismissListener) {
        this.dismissListener = dismissListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.tv_details_item_botom_sheet,
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
        var userRating = 0f
        binding.rating.setOnRatingBarChangeListener { _, rating, _ ->
            // Access the rating value here
            userRating = rating * 2

        }
        binding.buttonApply.setOnClickListener {
            dismissListener?.onBottomSheetDismissed()
            dismissListener?.passRatingValue(userRating)
            dismiss()
        }
    }
}

interface BottomSheetDismissListener {
    fun onBottomSheetDismissed()
    fun passRatingValue(rate:Float)
}