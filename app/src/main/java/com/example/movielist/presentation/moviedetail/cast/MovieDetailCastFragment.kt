package com.example.movielist.presentation.moviedetail.cast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.databinding.FragmentMovieDetailCastBinding
import com.example.movielist.domain.entity.MovieCredits
import com.example.movielist.presentation.moviedetail.MovieDetailViewModel
import com.example.movielist.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailCastFragment(val movieId: Int) : Fragment() {

    private lateinit var binding: FragmentMovieDetailCastBinding

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    private lateinit var  movieDetailCastAdapter: MovieDetailCastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailCastBinding.inflate(
            LayoutInflater.from(activity),
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDetailViewModel.getMovieCredits(movieId)
        movieDetailViewModel.movieCredits.observe(viewLifecycleOwner) {
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

    private fun setupRecyclerView() = binding.rvCredits.apply {
        movieDetailCastAdapter = MovieDetailCastAdapter()
        adapter = movieDetailCastAdapter
        layoutManager = LinearLayoutManager(activity)
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun processSuccess(data: MovieCredits) {
        movieDetailCastAdapter.movieMovieCastList = data.movieCast
    }

    private fun processFailure(exception: Exception) {

    }
}