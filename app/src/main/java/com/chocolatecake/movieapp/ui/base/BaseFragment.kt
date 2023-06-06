package com.chocolatecake.movieapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.chocolatecake.movieapp.BR
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseFragment<VDB : ViewDataBinding, STATE, EVENT> : Fragment() {
    @get:LayoutRes
    abstract val layoutIdFragment: Int
    abstract val viewModel: BaseViewModel<STATE,EVENT>

    private lateinit var _binding: VDB
    protected val binding: VDB
        get() = _binding


    abstract fun onSateChange(state: STATE)
    abstract fun onEvent(event: EVENT)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectLatest { viewModel.state.collectLatest { onSateChange(it) } }
        collectLatest { viewModel.event.collectLatest { onEvent(it) } }
    }

    protected fun collectLatest(collect: suspend CoroutineScope.() -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
               collect()
            }
        }
    }

    protected fun showSnackBar(messages: String) {
        Snackbar.make(binding.root, messages, Snackbar.LENGTH_SHORT).show()
    }

}