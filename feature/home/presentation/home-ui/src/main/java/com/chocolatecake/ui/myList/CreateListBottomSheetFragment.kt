package com.chocolatecake.ui.myList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.chocolatecake.ui.home.BR
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.BottomSheetCreateListBinding
import com.chocolatecake.ui.home.databinding.MyListBottomSheetCreateListBinding
import com.chocolatecake.viewmodel.myList.MyListViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateListBottomSheetFragment(private val createButton: CreateListener) : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetCreateListBinding
    val viewModel by activityViewModels<MyListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_create_list, container, false)
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
        binding.materialButtonCreate.setOnClickListener {
            createButton.onClickCreate(binding.textInputEditTextListName.text.toString())
        }
//        binding.materialButtonCreate.setOnClickListener {
//            viewLifecycleOwner.lifecycleScope.launch {
//                Log.i("gg", "applyCreateList:   emptr")
//                Log.i("gg", "applyCreateList:   emptr"+ binding.textInputEditTextListName.toString())
//                viewModel.onCreateList(binding.textInputEditTextListName.toString())
//            }
//            dismiss()
//        }
    }
}

interface CreateListener {
    fun onClickCreate(listName: String)
}