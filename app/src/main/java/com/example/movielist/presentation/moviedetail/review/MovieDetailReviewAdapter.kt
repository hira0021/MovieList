package com.example.movielist.presentation.moviedetail.review

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielist.R
import com.example.movielist.databinding.ItemReviewBinding
import com.example.movielist.domain.entity.MovieReviewResult
import com.example.movielist.util.Const

class MovieDetailReviewAdapter :
    RecyclerView.Adapter<MovieDetailReviewAdapter.MovieReviewViewHolder>() {

    inner class MovieReviewViewHolder(val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, movieReviewResult: MovieReviewResult) = binding.apply {
            Glide.with(ivUser.context)
                .load(Const.IMAGE_BASE_URL + movieReviewResult.movieReviewAuthorDetails.avatarPath)
                .error(R.drawable.ic_baseline_image_24)
                .into(ivUser)
            tvUsername.text = movieReviewResult.author
            /*val rating:Double = String.format("%.1f", movieReview.movieReviewAuthorDetails.rating).toDouble()
            tvRating.text = rating.toString()*/
            tvRating.text = movieReviewResult.movieReviewAuthorDetails.rating.toString()
            val date = movieReviewResult.createdAt
            val getDate = date.subSequence(0, 10)
            val getTime = date.subSequence(11, 16)
            tvReviewDate.text = context.getString(R.string.review_dates, getDate, getTime)
            //smtvReview.setContent(movieReviewResult.content)
            smtvReview.text = movieReviewResult.content
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<MovieReviewResult>() {
        override fun areItemsTheSame(oldItem: MovieReviewResult, newItem: MovieReviewResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieReviewResult, newItem: MovieReviewResult): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var movieReviewResultList: List<MovieReviewResult>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewViewHolder {
        return MovieReviewViewHolder(
            ItemReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.bind(context, movieReviewResultList[position])
    }

    override fun getItemCount() = movieReviewResultList.size
}