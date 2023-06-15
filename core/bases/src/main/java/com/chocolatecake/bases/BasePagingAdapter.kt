package com.chocolatecake.bases

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BasePagingAdapter<T : Any, VB : ViewDataBinding>(
    diffCallback: DiffUtil.ItemCallback<T>,
    private val listener: BaseInteractionListener
) : PagingDataAdapter<T, BasePagingAdapter<T, VB>.BaseViewHolder>(diffCallback) {

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<VB>(
            inflater,
            layoutId,
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            getItem(position)?.let { item ->
                holder.bind(item)
            }
        }
    }

    inner class ItemViewHolder(private val binding: VB) :
        BaseViewHolder(binding) {

        fun bind(item: T) {
            binding.apply {
                setVariable(BR.item, item)
                setVariable(BR.listener, listener)
                executePendingBindings()
            }
        }
    }

    abstract inner class BaseViewHolder(item: ViewDataBinding) : RecyclerView.ViewHolder(item.root)
}