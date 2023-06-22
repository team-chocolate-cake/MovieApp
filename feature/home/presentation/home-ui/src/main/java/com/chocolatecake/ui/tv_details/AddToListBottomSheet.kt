package com.chocolatecake.ui.tv_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.chocolatecake.ui.home.BR
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.MyListBottomSheetCreateListBinding
import com.chocolatecake.viewmodel.tv_details.TvDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddToListBottomSheet(private val creeateButton:CreateListener) : BottomSheetDialogFragment() {
    private lateinit var binding: MyListBottomSheetCreateListBinding
    val viewModel by activityViewModels<TvDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.my_list_bottom_sheet_create_list,
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
        binding.apply {
            viewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
            chipAddNewList.setOnClickListener {
                groupCreateList.visibility =
                    if (chipAddNewList.isChecked) View.VISIBLE else View.GONE
            }
            textViewClose.setOnClickListener { dismiss() }
            textViewDone.setOnClickListener { dismiss() }
        }

        viewModel.getUserLists()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                binding.chipGroupGenere.setGenreChips(state.userLists, viewModel)
            }
        }
        binding.materialButtonCreate.setOnClickListener{
            creeateButton.onClickCreate()
        }
    }
}

interface CreateListener{
    fun onClickCreate()
}