package com.chocolatecake.ui.tvShow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.chocolatecake.ui.home.BR
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FilterTvShowBottomSheetBinding
import com.chocolatecake.viewmodel.tv_shows.TVShowsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterTVShowsBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FilterTvShowBottomSheetBinding
    val viewModel by activityViewModels<TVShowsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.filter_tv_show_bottom_sheet, container, false)
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
        binding.buttonApplyFilter.setOnClickListener {
            viewModel.getData()
            dismiss()
        }
    }
}