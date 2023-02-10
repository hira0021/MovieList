package com.example.movielist.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.R
import com.example.movielist.databinding.ItemLoadBinding

class HomeLoaderAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<HomeLoaderAdapter.HomeLoaderViewHolder>() {

    inner class HomeLoaderViewHolder(val binding: ItemLoadBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState, retry: () -> Unit) {
            if (loadState is LoadState.Error) {
                binding.tvErrorMessage.setText(R.string.error_occured)
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.btnRetry.isVisible = loadState !is LoadState.Loading
            binding.btnRetry.setOnClickListener {
                retry()
            }
        }
    }

    override fun onBindViewHolder(holder: HomeLoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState, retry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): HomeLoaderViewHolder {
        return HomeLoaderViewHolder(
            ItemLoadBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}