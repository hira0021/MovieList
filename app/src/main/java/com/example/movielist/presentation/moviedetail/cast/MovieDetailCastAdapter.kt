package com.example.movielist.presentation.moviedetail.cast

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielist.R
import com.example.movielist.databinding.ItemCastBinding
import com.example.movielist.domain.entity.MovieCast
import com.example.movielist.util.Const

class MovieDetailCastAdapter : RecyclerView.Adapter<MovieDetailCastAdapter.MovieCastViewHolder>() {

    inner class MovieCastViewHolder(val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieCast: MovieCast) {
            Log.d("MYTAG", movieCast.name)
            binding.apply {
                Glide.with(ivCast.context)
                    .load(Const.IMAGE_BASE_URL + movieCast.profilePath)
                    .error(R.drawable.ic_baseline_image_24)
                    .into(ivCast)
                tvCastName.text = movieCast.name
                tvCastCharacter.text = movieCast.character
            }
        }
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<MovieCast>() {
        override fun areItemsTheSame(oldItem: MovieCast, newItem: MovieCast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieCast, newItem: MovieCast): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var movieMovieCastList: List<MovieCast>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder {
        return MovieCastViewHolder(
            ItemCastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {
        holder.bind(movieMovieCastList[position])
    }

    override fun getItemCount() = movieMovieCastList.size

    init {
        Log.d("MYTAG", movieMovieCastList.toString())
    }

}