package com.example.movielist.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.databinding.FragmentHomeBinding
import com.example.movielist.domain.entity.MovieDiscoverResult
import com.example.movielist.presentation.MainViewModel
import com.example.movielist.presentation.moviedetail.MovieDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var homeMovieAdapter: HomeMovieAdapter

    private lateinit var selectedMovieDiscoverResult: MovieDiscoverResult

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupRecyclerView()

        //get Genre list for discover movie
        mainViewModel.getGenreListForDiscoverMovie()
        mainViewModel.movieGenreList.observe(viewLifecycleOwner) {
            if (it != null) {
                homeMovieAdapter.movieGenreList = it.movieGenres
            }
        }

        // get Discover Movie List paging data
        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.pagingMovieList.collectLatest { pagingData ->
                homeMovieAdapter.submitData(pagingData)
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    /*private fun getMovie() {
        homeViewModel.getDiscoverMovie(1)
        homeViewModel.movieDiscoverData.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    binding.textHome.text = ""
                    homeMovieAdapter.movieDiscoverResults = it.data.movieDiscoverResults
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
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.rvHomeMovie.setHasFixedSize(true)
        homeMovieAdapter =
            HomeMovieAdapter { selectedItem: MovieDiscoverResult -> listMovieClicked(selectedItem) }
        binding.rvHomeMovie.layoutManager = LinearLayoutManager(activity)
        binding.rvHomeMovie.adapter = homeMovieAdapter.withLoadStateHeaderAndFooter(
            header = HomeLoaderAdapter { homeMovieAdapter.retry() },
            footer = HomeLoaderAdapter { homeMovieAdapter.retry() }
        )
        homeMovieAdapter.addLoadStateListener { loadState ->
            binding.rvHomeMovie.isVisible = loadState.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error
            binding.tvConnectionError.isVisible = loadState.source.refresh is LoadState.Error
            binding.tvNoResult.isVisible =
                loadState.refresh is LoadState.NotLoading && homeMovieAdapter.itemCount == 0
        }
        binding.retryButton.setOnClickListener {
            homeMovieAdapter.retry()
        }
    }

    private fun listMovieClicked(movieDiscoverResult: MovieDiscoverResult) {
        selectedMovieDiscoverResult = movieDiscoverResult
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra("movieId", selectedMovieDiscoverResult.id)
        startActivity(intent)
    }

}