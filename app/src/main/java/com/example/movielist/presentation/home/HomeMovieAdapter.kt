package com.example.movielist.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielist.databinding.ItemMovieBinding
import com.example.movielist.domain.entity.Movie

class HomeMovieAdapter : RecyclerView.Adapter<HomeMovieAdapter.HomeMovieViewHolder>() {

    inner class HomeMovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var movies: List<Movie>
        get() = differ.currentList
        set(value) {differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMovieViewHolder {
        return HomeMovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeMovieViewHolder, position: Int) {
        holder.binding.apply {
            val movie = movies[position]
            tvMovieTitle.text = movie.title
            tvMovieGenre.text = "Genre : " + movie.genre_ids
            tvMovieReleaseDate.text = "Release date : " + movie.release_date
            Glide.with(movieImageView.context)
                .load("https://image.tmdb.org/t/p/w500/" + movie.poster_path)
                .into(movieImageView)
            tvRating.text = movie.vote_average.toString()
        }
    }

    override fun getItemCount() = movies.size

}