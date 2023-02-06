package com.example.movielist.presentation.home

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.movielist.domain.UseCase.MovieUseCase
import com.example.movielist.domain.entity.MovieGenreList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val movieInteractor: MovieUseCase) : ViewModel() {

    val currentSearchQuery = MutableLiveData("")

    val pagingMovieList = currentSearchQuery.switchMap { queryString ->
        movieInteractor.getPagerDiscoverMovies(queryString).cachedIn(viewModelScope)
    }

    /*currentSearchQuery
    .debounce(300)
    .distinctUntilChanged()
    .filter {
        it.trim().isNotEmpty()
    }
    .mapLatest { query ->
        movieInteractor.getPagerDiscoverMovies(query)
    }*/

    //movieInteractor.getPagerDiscoverMovies(currentSearchQuery).cachedIn(viewModelScope)
    //val pagingMovieList: Flow<PagingData<MovieDiscoverResult>> = _pagingMovieList

    private val _movieGenreList: MutableLiveData<MovieGenreList> = MutableLiveData()
    val movieGenreList: LiveData<MovieGenreList> = _movieGenreList

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
                _movieGenreList.value = it
            }
    }

}