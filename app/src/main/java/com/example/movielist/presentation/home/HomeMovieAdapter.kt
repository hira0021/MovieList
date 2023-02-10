package com.example.movielist.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielist.R
import com.example.movielist.databinding.ItemMovieBinding
import com.example.movielist.domain.entity.MovieGenre
import com.example.movielist.domain.entity.MovieDiscoverResult
import com.example.movielist.util.Const

class HomeMovieAdapter(
    private val clickListener: (MovieDiscoverResult) -> Unit,
) : PagingDataAdapter<MovieDiscoverResult, HomeMovieAdapter.HomeMovieViewHolder>(diffCallback) {

    inner class HomeMovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, movieDiscoverResult: MovieDiscoverResult?, clickListener: (MovieDiscoverResult) -> Unit) {
            binding.apply {
                tvMovieTitle.text = movieDiscoverResult?.title
                val genreName = setGenreIdToName(movieDiscoverResult!!)
                tvMovieGenre.text = context.getString(
                    R.string.string_genre, genreName.toString()
                        .replace('[', ' ')
                        .replace(']', ' ')
                )
                tvMovieReleaseDate.text =
                    context.getString(R.string.string_release_date, movieDiscoverResult.releaseDate)
                Glide.with(movieImageView.context)
                    .load(Const.IMAGE_BASE_URL + movieDiscoverResult.posterPath)
                    .into(movieImageView)
                tvRating.text = movieDiscoverResult.voteAverage.toString()

                binding.cardMovie.setOnClickListener {
                    clickListener(movieDiscoverResult)
                }
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<MovieDiscoverResult>() {
            override fun areItemsTheSame(oldItem: MovieDiscoverResult, newItem: MovieDiscoverResult): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieDiscoverResult, newItem: MovieDiscoverResult): Boolean {
                return oldItem == newItem
            }
        }
    }

    /*private val diffCallback = object : DiffUtil.ItemCallback<MovieDiscoverResult>() {
        override fun areItemsTheSame(oldItem: MovieDiscoverResult, newItem: MovieDiscoverResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieDiscoverResult, newItem: MovieDiscoverResult): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var movieDiscoverResults: List<MovieDiscoverResult>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }*/
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
        val currentItem = getItem(position)

        holder.bind(context, currentItem, clickListener)
    }

    fun setGenreIdToName(movieDiscoverResult: MovieDiscoverResult): List<String> {
        var filteredGenre: List<String> = listOf()
        for (i in movieGenreList.indices) {
            for (j in movieDiscoverResult.genreIds.indices) {
                if (movieGenreList[i].id == movieDiscoverResult.genreIds[j]) {
                    val genre = movieGenreList[i]
                    filteredGenre = filteredGenre + genre.name
                }
            }
        }
        return filteredGenre
    }

}