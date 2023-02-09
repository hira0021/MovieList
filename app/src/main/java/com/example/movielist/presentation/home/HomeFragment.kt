package com.example.movielist.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.databinding.FragmentHomeBinding
import com.example.movielist.domain.entity.MovieDiscoverResult
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

    private val homeViewModel: HomeViewModel by activityViewModels()

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

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupRecyclerView()

        //get Genre list for discover movie
        homeViewModel.getGenreListForDiscoverMovie()
        homeViewModel.movieGenreList.observe(viewLifecycleOwner) {
            if (it != null) {
                homeMovieAdapter.movieGenreList = it.movieGenres
            }
        }

        // get Discover Movie List paging data
       lifecycleScope.launch {
           /*homeViewModel.pagingMovieList.observe(viewLifecycleOwner) { pagingData ->
               homeMovieAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
           }*/

           homeViewModel.pagingMovieList.collectLatest { pagingData ->
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

    private fun setupRecyclerView() = binding.rvHomeMovie.apply {
        setHasFixedSize(true)
        homeMovieAdapter = HomeMovieAdapter { selectedItem: MovieDiscoverResult -> listMovieClicked(selectedItem) }
        adapter = homeMovieAdapter.withLoadStateHeaderAndFooter(
            header = HomeLoaderAdapter(),
            footer = HomeLoaderAdapter()

        )
        layoutManager = LinearLayoutManager(activity)
    }

    private fun listMovieClicked(movieDiscoverResult: MovieDiscoverResult) {
        selectedMovieDiscoverResult = movieDiscoverResult
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra("movieId", selectedMovieDiscoverResult.id)
        startActivity(intent)
    }

}