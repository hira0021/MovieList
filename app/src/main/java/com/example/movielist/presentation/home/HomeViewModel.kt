package com.example.movielist.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielist.domain.entity.DiscoverMovie
import com.example.movielist.domain.UseCase.MovieUseCase
import com.example.movielist.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val movieInteractor: MovieUseCase) : ViewModel() {

    private val _discoverMovieData: MutableLiveData<DataState<DiscoverMovie>> = MutableLiveData()
    val discoverMovieData: LiveData<DataState<DiscoverMovie>> = _discoverMovieData

    fun setStateEvent() = viewModelScope.launch {
        movieInteractor.getDiscoverMovie()
            .flowOn(Dispatchers.IO)
            .catch { e ->

            }
            .collect {
                _discoverMovieData.value = it
            }
    }
}