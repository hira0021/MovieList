package com.example.movielist.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielist.domain.entity.MovieDiscover
import com.example.movielist.domain.UseCase.MovieUseCase
import com.example.movielist.domain.entity.MovieGenreList
import com.example.movielist.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val movieInteractor: MovieUseCase) : ViewModel() {

    private val _MovieDiscoverData: MutableLiveData<DataState<MovieDiscover>> = MutableLiveData()
    val movieDiscoverData: LiveData<DataState<MovieDiscover>> = _MovieDiscoverData

    private val _Movie_genreList: MutableLiveData<MovieGenreList> = MutableLiveData()
    val movieGenreList: LiveData<MovieGenreList> = _Movie_genreList

    fun getDiscoverMovie() = viewModelScope.launch {
        movieInteractor.getDiscoverMovies()
            .flowOn(Dispatchers.IO)
            .catch { e ->
                Log.e("HomeViewModel", e.toString())
            }
            .collect {
                _MovieDiscoverData.value = it
            }
    }

    fun getGenreListForDiscoverMovie() = viewModelScope.launch {
        movieInteractor.getGenreList()
            .flowOn(Dispatchers.IO)
            .catch { e ->
                Log.e("HomeViewModel", e.toString())
            }
            .collect {
                _Movie_genreList.value = it
            }
    }

}