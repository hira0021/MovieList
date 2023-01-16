package com.example.movielist.presentation.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielist.R
import com.example.movielist.databinding.ItemMovieBinding
import com.example.movielist.domain.entity.MovieGenre
import com.example.movielist.domain.entity.MovieInfo
import com.example.movielist.util.Const

class HomeMovieAdapter(
    private val clickListener: (MovieInfo) -> Unit,
) : RecyclerView.Adapter<HomeMovieAdapter.HomeMovieViewHolder>() {


    inner class HomeMovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, movieInfo: MovieInfo, clickListener: (MovieInfo) -> Unit) {
            Log.d("MYTAG", movieInfo.toString())
            binding.apply {
                tvMovieTitle.text = movieInfo.title
                val genreName = setGenreIdToName(movieInfo)
                tvMovieGenre.text = context.getString(
                    R.string.string_genre, genreName.toString()
                        .replace('[', ' ')
                        .replace(']', ' ')
                )
                tvMovieReleaseDate.text =
                    context.getString(R.string.string_release_date, movieInfo.releaseDate)
                Glide.with(movieImageView.context)
                    .load(Const.IMAGE_BASE_URL + movieInfo.posterPath)
                    .into(movieImageView)
                tvRating.text = movieInfo.voteAverage.toString()

                binding.cardMovie.setOnClickListener {
                    clickListener(movieInfo)
                }
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<MovieInfo>() {
        override fun areItemsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var movieInfos: List<MovieInfo>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }
    var movieGenreList: List<MovieGenre> = listOf()

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
        //get context and send it to bind method
        val context = holder.itemView.context

        holder.bind(context, movieInfos[position], clickListener)
    }

    fun setGenreIdToName(movieInfo: MovieInfo): List<String> {
        var filteredGenre: List<String> = listOf()
        for (i in movieGenreList.indices) {
            for (j in movieInfo.genreIds.indices) {
                if (movieGenreList[i].id == movieInfo.genreIds[j]) {
                    val genre = movieGenreList[i]
                    filteredGenre = filteredGenre + genre.name
                }
            }
        }
        return filteredGenre
    }

    override fun getItemCount() = movieInfos.size

}