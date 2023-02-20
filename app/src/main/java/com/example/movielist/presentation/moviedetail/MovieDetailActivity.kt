package com.example.movielist.presentation.moviedetail

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.example.movielist.databinding.ActivityMovieDetailBinding
import com.example.movielist.domain.entity.MovieDetail
import com.example.movielist.presentation.moviedetail.cast.MovieDetailCastFragment
import com.example.movielist.presentation.moviedetail.overview.MovieDetailOverviewFragment
import com.example.movielist.presentation.moviedetail.review.MovieDetailReviewFragment
import com.example.movielist.util.Const
import com.example.movielist.util.DataState
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    private var movieId: Int = 0

    var movieDetailCache: MovieDetail = MovieDetail()

    // title TabLayout
    private val tabTittle = arrayOf("Overview", "Cast", "Review")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        movieId = intent.getIntExtra("movieId", 0)
        movieDetailViewModel.setMovieId(movieId)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setupViewPager()

        getMovieDetail()

        binding.fabFavorite.setOnClickListener {
            val posterPath = saveImageToGallery(binding.ivPoster, true)
            val backdropPath = saveImageToGallery(binding.ivBackdrop, false)
            movieDetailCache.posterPath = posterPath
            movieDetailCache.backdropPath = backdropPath
            movieDetailViewModel.saveMovie(movieDetailCache)
        }
    }

    private fun saveImageToGallery(imageView: ShapeableImageView, posterPath: Boolean): String? {
        //Check permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }

        val bitmap = Bitmap.createBitmap(imageView.width, imageView.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        imageView.draw(canvas)

        val imageDir = File(Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES), "MovieImage")
        if (!imageDir.exists()) {
            imageDir.mkdir()
        }

        val imageFile: File
        if (posterPath) {
            val posterDir = File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MovieImage/Poster")
            if (!posterDir.exists()) {
                posterDir.mkdir()
            }
            imageFile = File(posterDir,"${movieId}.jpg")
        } else {
            val backdropDir = File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MovieImage/Backdrop")
            if (!backdropDir.exists()) {
                backdropDir.mkdir()
            }
            imageFile = File(backdropDir,"${movieId}.jpg")
        }

        try {
            val fileOutputStream = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()

            MediaScannerConnection.scanFile(this, arrayOf(imageFile.toString()), null, null)
            //Toast.makeText(this, "Image saved to gallery", Toast.LENGTH_SHORT).show()

            return imageFile.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
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
        movieDetailViewModel.getMovieCache()
        movieDetailViewModel.movieFavoriteCache.observe(this) {

        }

        movieDetailViewModel.getMovieDetail()
        movieDetailViewModel.getMovieCredits()
        movieDetailViewModel.getMovieReviews()
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

        movieDetailCache = movieDetail

        // Glide ProgressBar
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(this)
            .load(Const.IMAGE_BASE_URL + movieDetail.backdropPath)
            .placeholder(circularProgressDrawable)
            .into(binding.ivBackdrop)
        Glide.with(this)
            .load(Const.IMAGE_BASE_URL + movieDetail.posterPath)
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
                0 -> MovieDetailOverviewFragment()
                1 -> MovieDetailCastFragment()
                2 -> MovieDetailReviewFragment()
                else -> MovieDetailOverviewFragment()
            }
        }
    }
}

