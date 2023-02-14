package com.example.movielist.presentation.moviedetail.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.databinding.FragmentMovieDetailReviewBinding
import com.example.movielist.domain.entity.MovieReview
import com.example.movielist.presentation.moviedetail.MovieDetailViewModel
import com.example.movielist.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailReviewFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailReviewBinding

    private val movieDetailViewModel: MovieDetailViewModel by activityViewModels()

    private lateinit var movieDetailReviewAdapter: MovieDetailReviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDetailViewModel.movieReviews.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    showLoading()
                }
                is DataState.Success -> {
                    processSuccess(it.data)
                    stopLoading()
                }
                is DataState.Error -> {
                    processFailure(it.exception)
                    stopLoading()
                }
            }
        }

        setupRecyclerView()

    }

    private fun setupRecyclerView() = binding.rvReview.apply {
        movieDetailReviewAdapter = MovieDetailReviewAdapter()
        adapter = movieDetailReviewAdapter
        layoutManager = LinearLayoutManager(activity)
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun processFailure(exception: Exception) {

    }

    private fun processSuccess(data: MovieReview) {
        movieDetailReviewAdapter.movieReviewResultList = data.movieReviewResults
    }

}