package com.example.movielist.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.databinding.FragmentHomeBinding
import com.example.movielist.domain.entity.MovieDiscoverResult
import com.example.movielist.presentation.moviedetail.MovieDetailActivity
import com.example.movielist.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var homeMovieAdapter: HomeMovieAdapter

    private lateinit var selectedMovieDiscoverResult: MovieDiscoverResult

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        //get Genre list for discover movie
        homeViewModel.getGenreListForDiscoverMovie()
        homeViewModel.movieGenreList.observe(viewLifecycleOwner) {
            if (it != null) {
                homeMovieAdapter.movieGenreList = it.movieGenres
            }
        }

        // get Discover Movie List
        homeViewModel.getDiscoverMovie()
        homeViewModel.movieDiscoverData.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    discoverMovieProcessSuccess(it.data.movieDiscoverResults)
                    stopLoading()
                }
                is DataState.Error -> {
                    discoverMovieProcessFailure(it.exception)
                    stopLoading()
                }
                is DataState.Loading -> {
                    showLoading()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() = binding.rvHomeMovie.apply {
        homeMovieAdapter = HomeMovieAdapter { selectedItem: MovieDiscoverResult -> listMovieClicked(selectedItem) }
        adapter = homeMovieAdapter
        layoutManager = LinearLayoutManager(activity)
    }

    private fun listMovieClicked(movieDiscoverResult: MovieDiscoverResult) {
        selectedMovieDiscoverResult = movieDiscoverResult
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra("movieId", selectedMovieDiscoverResult.id)
        startActivity(intent)
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun discoverMovieProcessSuccess(data: List<MovieDiscoverResult>) {
        binding.textHome.text = ""
        homeMovieAdapter.movieDiscoverResults = data
    }

    private fun discoverMovieProcessFailure(e: Exception) {
        binding.textHome.text = e.toString()
    }
}