package com.chocolatecake.ui.tv_details

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
import com.chocolatecake.ui.home.databinding.MyListBottomSheetCreateListBinding
import com.chocolatecake.ui.movieDetails.setGenreChips
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddToListBottomSheet(private val creeateButton: CreateListener) :
    BottomSheetDialogFragment() {
    private lateinit var binding: MyListBottomSheetCreateListBinding
    val viewModel by activityViewModels<MovieDetailsViewModel>()

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
        }

        viewModel.getUserLists()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.map { it.userLists }.distinctUntilChanged().collectLatest {
                Log.i("list","new list => ${viewModel.state.value.userLists}")
                binding.chipGroupGenere.setGenreChips(viewModel.state.value.userLists, viewModel)
                viewModel.emptyUserLists()
            }
        }
        binding.materialButtonCreate.setOnClickListener {
            creeateButton.onClickCreate(binding.textInputEditTextListName.text.toString())
            binding.chipGroupGenere.removeViewsInLayout(0,binding.chipGroupGenere.childCount -1)
            viewLifecycleOwner.lifecycleScope.launch {
                delay(300)
                onViewCreated(view, savedInstanceState)
            }
        }
        binding.textViewDone.setOnClickListener {
            creeateButton.onDone(viewModel.state.value.userSelectedLists)
            dismiss()
        }

    }
}

interface CreateListener {
    fun onClickCreate(listName: String)
    fun onDone(listsId: List<Int>)
}