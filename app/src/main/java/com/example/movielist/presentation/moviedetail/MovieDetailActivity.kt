package com.example.movielist.presentation.moviedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.movielist.databinding.ActivityMovieDetailBinding
import com.example.movielist.domain.entity.MovieDetail
import com.example.movielist.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieId = intent.getIntExtra("movieId", 0)
        getMovieDetail()
    }

    private fun getMovieDetail() {
        movieDetailViewModel.getMovieDetail(movieId)
        movieDetailViewModel.movieDetail.observe(this) {
            when (it) {
                is DataState.Success -> {
                    processSuccess(it.data)
                    stopLoading()
                }
                is DataState.Error -> {
                    processFailure(it.exception)
                    stopLoading()
                }
                is DataState.Loading -> {
                    showLoading()
                }
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun processSuccess(movieDetail: MovieDetail) {
        binding.tvInfo.text = movieDetail.title
    }

    private fun processFailure(exception: Exception) {
        binding.tvInfo.text = exception.toString()
    }
}