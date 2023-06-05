package com.chocolatecake.movieapp.ui.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.chocolatecake.movieapp.BR
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.databinding.BottomSheetSearchFilterBinding
import com.chocolatecake.movieapp.ui.search.SearchAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterMovieBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetSearchFilterBinding
    val viewModel by activityViewModels<SearchViewModel>()
    private lateinit var searchAdapter: SearchAdapter

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
        searchAdapter = SearchAdapter(mutableListOf(), viewModel)
        binding.buttonFilter.setOnClickListener {
            viewModel.getData()
            dismiss()
        }
    }

}