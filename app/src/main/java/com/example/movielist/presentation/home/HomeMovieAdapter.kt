package com.example.movielist.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielist.databinding.ItemMovieBinding
import com.example.movielist.domain.entity.Genre
import com.example.movielist.domain.entity.Movie
import com.example.movielist.util.Const

class HomeMovieAdapter(
    private val clickListener: (Movie) -> Unit
) : RecyclerView.Adapter<HomeMovieAdapter.HomeMovieViewHolder>() {

    inner class HomeMovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, clickListener: (Movie) -> Unit) {
            binding.apply {
                tvMovieTitle.text = movie.title
                val genreName = setGenreIdToName(movie)
                tvMovieGenre.text = "Genre : " +
                        genreName.toString()
                            .replace('[', ' ')
                            .replace(']', ' ')
                tvMovieReleaseDate.text = "Release date : " + movie.release_date
                Glide.with(movieImageView.context)
                    .load(Const.IMAGE_BASE_URL + movie.poster_path)
                    .into(movieImageView)
                tvRating.text = movie.vote_average.toString()

                binding.cardMovie.setOnClickListener {
                    clickListener(movie)
                }
            }
        }
    }

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
        set(value) {
            differ.submitList(value)
        }
    var genreList: List<Genre> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMovieViewHolder {
        return HomeMovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    override fun onBindViewHolder(holder: HomeMovieViewHolder, position: Int) {
        holder.bind(movies[position], clickListener)
    }

    fun setGenreIdToName(movie: Movie): List<String> {
        var filteredGenre: List<String> = listOf()
        for (i in genreList.indices) {
            for (j in movie.genre_ids.indices) {
                if (genreList[i].id == movie.genre_ids[j]) {
                    val genre = genreList[i]
                    filteredGenre = filteredGenre + genre.name
                }
            }
        }
        return filteredGenre
    }

    override fun getItemCount() = movies.size

}