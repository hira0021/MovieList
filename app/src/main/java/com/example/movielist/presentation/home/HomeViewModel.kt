package com.example.movielist.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movielist.domain.UseCase.MovieUseCase
import com.example.movielist.domain.entity.MovieDiscoverResult
import com.example.movielist.domain.entity.MovieGenreList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val movieInteractor: MovieUseCase) : ViewModel() {

    /*private val _movieDiscoverData: MutableLiveData<DataState<MovieDiscover>> = MutableLiveData()
    val movieDiscoverData: LiveData<DataState<MovieDiscover>> = _movieDiscoverData*/

    private val _pagingMovieList = movieInteractor.getPagerDiscoverMovies().cachedIn(viewModelScope)
    val pagingMovieList: Flow<PagingData<MovieDiscoverResult>> = _pagingMovieList

    private val _movie_genreList: MutableLiveData<MovieGenreList> = MutableLiveData()
    val movieGenreList: LiveData<MovieGenreList> = _movie_genreList

    /*fun getDiscoverMovie(page: Int) = viewModelScope.launch {
        movieInteractor.getDiscoverMovies(page)
            .flowOn(Dispatchers.IO)
            .catch { e ->
                Log.e("HomeViewModel", e.toString())
            }
            .collect {
                _movieDiscoverData.value = it
            }

    }*/

    fun getGenreListForDiscoverMovie() = viewModelScope.launch {
        movieInteractor.getGenreList()
            .flowOn(Dispatchers.IO)
            .catch { e ->
                Log.e("HomeViewModel", e.toString())
            }
            .collect {
                _movie_genreList.value = it
            }
    }

}