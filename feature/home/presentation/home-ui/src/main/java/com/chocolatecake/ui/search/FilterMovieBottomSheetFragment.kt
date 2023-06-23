package com.chocolatecake.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.chocolatecake.ui.home.BR
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.BottomSheetSearchFilterBinding
import com.chocolatecake.ui.search.utils.setGenres
import com.chocolatecake.viewmodel.search.SearchViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilterMovieBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetSearchFilterBinding
    val viewModel by activityViewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_search_filter, container, false)
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
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.map { it.genres }.distinctUntilChanged().collectLatest {
                binding.chipGroupFilter.setGenres(
                    viewModel.state.value.genres,
                    viewModel,
                    viewModel.state.value.selectedGenresId
                )
            }

        }
    }
}