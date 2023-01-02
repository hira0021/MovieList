package com.example.movielist.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.databinding.FragmentHomeBinding
import com.example.movielist.domain.Entity.Movie
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        homeViewModel.setStateEvent()
        homeViewModel.discoverMovieData.observe(viewLifecycleOwner) {
            Log.d("MYTAG", "asdf")
            when (it) {
                is DataState.Success -> {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() = binding.rvHomeMovie.apply {
        homeMovieAdapter = HomeMovieAdapter()
        adapter = homeMovieAdapter
        layoutManager = LinearLayoutManager(activity)
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun processSuccess(data: List<Movie>) {
        binding.textHome.text = ""
        if (data!=null) {
            homeMovieAdapter.movies = data
        }
    }

    private fun processFailure(e: Exception) {
        Log.d("MYTAG", "failure")
        binding.textHome.text = e.toString()
    }
}