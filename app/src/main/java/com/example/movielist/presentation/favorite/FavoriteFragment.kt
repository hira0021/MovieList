package com.example.movielist.presentation.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.data.local.entity.MovieFavoriteListCacheEntity
import com.example.movielist.databinding.FragmentFavoriteBinding
import com.example.movielist.presentation.MainViewModel
import com.example.movielist.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var favoriteMovieAdapter: FavoriteMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mainViewModel.getFavoriteMovieListCache()
        mainViewModel.movieFavoriteList.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    Log.d("MYTAG", it.data.size.toString())
                    processSucces(it.data)
                }
                is DataState.Error -> {
                    Log.d("MYTAG", it.toString())
                }
                is DataState.Loading -> {
                    Log.d("MYTAG", "loading")
                }
            }
        }

        setupRecyclerView()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun processSucces(data: List<MovieFavoriteListCacheEntity>) {
        favoriteMovieAdapter.movieFavoriteList = data
    }

    private fun setupRecyclerView() {
        favoriteMovieAdapter = FavoriteMovieAdapter()
        binding.rvFavoriteMovie.layoutManager = LinearLayoutManager(activity)
        binding.rvFavoriteMovie.adapter = favoriteMovieAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}