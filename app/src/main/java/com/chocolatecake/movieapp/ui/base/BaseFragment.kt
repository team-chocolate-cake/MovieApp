package com.chocolatecake.movieapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.chocolatecake.movieapp.BR
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VDB : ViewDataBinding> : Fragment() {
    @get:LayoutRes
    abstract val layoutIdFragment: Int
    abstract val viewModel: ViewModel

    private lateinit var _binding: VDB
    protected val binding: VDB
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutIdFragment, container, false)
        _binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
            return root
        }
    }

    fun createSnackBar(text: String) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            text, Snackbar.LENGTH_LONG
        ).show();
    }
}