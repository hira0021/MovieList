package com.example.movielist.presentation.moviedetail.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielist.R
import com.example.movielist.databinding.ItemProductionCompaniesBinding
import com.example.movielist.domain.entity.MovieProductionCompany
import com.example.movielist.util.Const

class MovieDetailProductionCompaniesAdapter : RecyclerView.Adapter<MovieDetailProductionCompaniesAdapter.MovieProductionCompaniesViewHolder>() {

    inner class MovieProductionCompaniesViewHolder(val binding: ItemProductionCompaniesBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(movieProductionCompany: MovieProductionCompany) {
                binding.apply {
                    textView.text = movieProductionCompany.name
                    Glide.with(ivProductionCompanies.context)
                        .load(Const.IMAGE_BASE_URL + movieProductionCompany.logoPath)
                        .error(R.drawable.ic_baseline_image_24)
                        .into(ivProductionCompanies)
                }
            }
        }

    var productionCompanies: List<MovieProductionCompany> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieProductionCompaniesViewHolder {
        return MovieProductionCompaniesViewHolder(
            ItemProductionCompaniesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieProductionCompaniesViewHolder, position: Int) {
        holder.bind(productionCompanies[position])
    }

    override fun getItemCount() = productionCompanies.size
}