package com.example.movielist.presentation.moviedetail

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.example.movielist.databinding.ActivityMovieDetailBinding
import com.example.movielist.domain.entity.MovieDetail
import com.example.movielist.presentation.moviedetail.cast.MoviewDetailCastFragment
import com.example.movielist.presentation.moviedetail.overview.MovieDetailOverviewFragment
import com.example.movielist.presentation.moviedetail.review.MovieDetailReviewFragment
import com.example.movielist.util.Const
import com.example.movielist.util.DataState
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    private var movieId: Int = 0

    // title TabLayout
    private val tabTittle = arrayOf("Overview", "Cast", "Review")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setupViewPager()

        movieId = intent.getIntExtra("movieId", 0)
        getMovieDetail()
    }

    private fun setupViewPager() {
        binding.apply {
            viewPager.adapter = MovieTabAdapter(supportFragmentManager, lifecycle)

            TabLayoutMediator(
                tabLayout,
                viewPager
            ) { tab, position ->
                tab.text = tabTittle[position]
            }.attach()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // actionbar back
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getMovieDetail() {
        movieDetailViewModel.getMovieDetail(movieId)
        movieDetailViewModel.movieDetail.observe(this) {
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
    }

    private fun showLoading() {
        //binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        //binding.progressBar.visibility = View.GONE
    }

    private fun processSuccess(movieDetail: MovieDetail) {

        // Glide ProgressBar
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(this)
            .load(Const.IMAGE_BASE_URL + movieDetail.backdrop_path)
            .placeholder(circularProgressDrawable)
            .into(binding.ivBackdrop)
        Glide.with(this)
            .load(Const.IMAGE_BASE_URL + movieDetail.poster_path)
            .placeholder(circularProgressDrawable)
            .into(binding.ivPoster)

        binding.tvMovieTitle.text = movieDetail.title

    }

    private fun processFailure(exception: Exception) {
    }

    inner class MovieTabAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        override fun getItemCount() = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> MovieDetailOverviewFragment(movieId)
                1 -> MoviewDetailCastFragment(movieId)
                2 -> MovieDetailReviewFragment(movieId)
                else -> MovieDetailOverviewFragment(movieId)
            }
        }
    }
}

