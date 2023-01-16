package com.example.movielist.presentation.moviedetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielist.domain.UseCase.MovieUseCase
import com.example.movielist.domain.entity.MovieCredits
import com.example.movielist.domain.entity.MovieDetail
import com.example.movielist.domain.entity.MovieReview
import com.example.movielist.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(val movieInteractor: MovieUseCase) : ViewModel() {

    private val _movieDetail: MutableLiveData<DataState<MovieDetail>> = MutableLiveData()
    val movieDetail: LiveData<DataState<MovieDetail>> = _movieDetail

    private val _movieCredits: MutableLiveData<DataState<MovieCredits>> = MutableLiveData()
    val movieCredits: LiveData<DataState<MovieCredits>> = _movieCredits

    private val _movieReviews: MutableLiveData<DataState<MovieReview>> = MutableLiveData()
    val movieReviews: LiveData<DataState<MovieReview>> = _movieReviews

    fun getMovieDetail(movieId: Int) = viewModelScope.launch {
        movieInteractor.getMovieDetail(movieId)
            .flowOn(Dispatchers.IO)
            .catch { e ->
                Log.e("MovieDetailViewModel", e.toString())
            }
            .collect {
                _movieDetail.value = it
            }
    }

    fun getMovieCredits(movieId: Int) = viewModelScope.launch {
        movieInteractor.getMovieCredits(movieId)
            .flowOn(Dispatchers.IO)
            .catch { e ->
                Log.e("MovieDetailViewModel", e.toString())
            }
            .collect {
                _movieCredits.value = it
            }
    }

    fun getMovieReviews(movieId: Int) = viewModelScope.launch {
        movieInteractor.getMovieReview(movieId)
            .flowOn(Dispatchers.IO)
            .catch { e ->
                Log.e("MovieDetailViewModel", e.toString())
            }
            .collect {
                _movieReviews.value = it
            }
    }

}