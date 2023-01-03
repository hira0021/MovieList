package com.example.movielist.presentation.moviedetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielist.domain.UseCase.MovieUseCase
import com.example.movielist.domain.entity.MovieDetail
import com.example.movielist.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(val movieInteractor: MovieUseCase): ViewModel() {

    private val _movieDetail: MutableLiveData<DataState<MovieDetail>> = MutableLiveData()
    val movieDetail: LiveData<DataState<MovieDetail>> = _movieDetail

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

}