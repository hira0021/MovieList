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
import com.example.movielist.domain.entity.Cast
import com.example.movielist.util.Const

class MovieCastAdapter : RecyclerView.Adapter<MovieCastAdapter.MovieCastViewHolder>() {

    inner class MovieCastViewHolder(val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: Cast) {
            Log.d("MYTAG", cast.name)
            binding.apply {
                Glide.with(ivCast.context)
                    .load(Const.IMAGE_BASE_URL + cast.profile_path)
                    .error(R.drawable.ic_baseline_image_24)
                    .into(ivCast)
                tvCastName.text = cast.name
                tvCastCharacter.text = cast.character
            }
        }
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var movieCastList: List<Cast>
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
        holder.bind(movieCastList[position])
    }

    override fun getItemCount() = movieCastList.size

    init {
        Log.d("MYTAG", movieCastList.toString())
    }

}