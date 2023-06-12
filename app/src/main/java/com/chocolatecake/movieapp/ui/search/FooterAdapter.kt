package com.chocolatecake.movieapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chocolatecake.movieapp.databinding.ItemFooterBinding
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
class FooterAdapter (private val retry: () -> Unit) :
    LoadStateAdapter<FooterAdapter.FooterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        FooterViewHolder(
            ItemFooterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) =
        holder.bind(loadState)


    inner class FooterViewHolder(val binding: ItemFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progress.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState is LoadState.Error
            }
        }
    }
}