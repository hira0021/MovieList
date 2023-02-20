package com.example.movielist.presentation.moviedetail.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movielist.R
import com.example.movielist.databinding.FragmentMovieDetailOverviewBinding
import com.example.movielist.domain.entity.MovieDetail
import com.example.movielist.presentation.moviedetail.MovieDetailViewModel
import com.example.movielist.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class MovieDetailOverviewFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailOverviewBinding

    private val movieDetailViewModel: MovieDetailViewModel by activityViewModels()

    private var genreListName: MutableList<String> = ArrayList()

    private lateinit var movieDetailProductionCompaniesAdapter: MovieDetailProductionCompaniesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailOverviewBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDetailViewModel.movieDetail.observe(viewLifecycleOwner) {
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

        setupRecyclerView()

    }

    fun setupRecyclerView() = binding.rvProductionCompanies.apply {
        movieDetailProductionCompaniesAdapter = MovieDetailProductionCompaniesAdapter()
        adapter = movieDetailProductionCompaniesAdapter
        //layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        val myGridLayoutManager = object : GridLayoutManager(activity, 3) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        layoutManager = myGridLayoutManager
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun processFailure(exception: java.lang.Exception) {
        /*binding.tvError.visibility = View.VISIBLE
        binding.tvError.text = exception.toString()*/
    }

    private fun processSuccess(data: MovieDetail) {
        binding.layoutInformation.visibility = View.VISIBLE

        //movieDetailViewModel.movieDetailCache.movieGenres = genreListName

        movieDetailProductionCompaniesAdapter.productionCompanies = data.productionCompanies

        binding.apply {
            for (item in data.movieGenres) {
                genreListName.add(item.name)
            }
            tvMovieGenre.text = genreListName.joinToString()
            val rating = (data.voteAverage * 100.0).roundToInt() / 100.0
            tvMovieRating.text = getString(R.string.string_rating, rating.toString())
            tvMovieTotalVote.text = data.voteCount.toString()
            tvMovieReleaseDate.text = data.releaseDate
            tvMovieSynopsis.text = data.overview
        }
    }

}