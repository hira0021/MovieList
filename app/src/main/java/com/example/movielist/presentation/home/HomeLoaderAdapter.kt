package com.example.movielist.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.databinding.ItemLoadBinding

class HomeLoaderAdapter: LoadStateAdapter<HomeLoaderAdapter.HomeLoaderViewHolder>() {

    class HomeLoaderViewHolder(val binding: ItemLoadBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: HomeLoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
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