package com.example.movielist.presentation.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielist.R
import com.example.movielist.data.local.entity.MovieFavoriteListCacheEntity
import com.example.movielist.databinding.ItemMovieBinding

class FavoriteMovieAdapter : RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieViewHolder>() {

    inner class FavoriteMovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieFavoriteListCacheEntity: MovieFavoriteListCacheEntity, context: Context) {
            binding.apply {
                Glide.with(movieImageView.context)
                    .load(movieFavoriteListCacheEntity.posterPath)
                    .into(movieImageView)
                tvMovieTitle.text = movieFavoriteListCacheEntity.title
                tvMovieGenre.text = context.getString(
                    R.string.string_genre, movieFavoriteListCacheEntity.genreList
                )
                tvMovieReleaseDate.text =
                    context.getString(
                        R.string.string_release_date,
                        movieFavoriteListCacheEntity.releaseDate
                    )
                tvRating.text = movieFavoriteListCacheEntity.voteAverage.toString()
            }
        }
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<MovieFavoriteListCacheEntity>() {
        override fun areItemsTheSame(
            oldItem: MovieFavoriteListCacheEntity,
            newItem: MovieFavoriteListCacheEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieFavoriteListCacheEntity,
            newItem: MovieFavoriteListCacheEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var movieFavoriteList: List<MovieFavoriteListCacheEntity>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        return FavoriteMovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.bind(movieFavoriteList[position], context)
    }

    override fun getItemCount() = movieFavoriteList.size
}