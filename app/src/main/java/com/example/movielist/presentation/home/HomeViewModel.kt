package com.example.movielist.presentation.home

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import com.example.movielist.domain.UseCase.MovieUseCase
import com.example.movielist.domain.entity.MovieDiscoverResult
import com.example.movielist.domain.entity.MovieGenreList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val movieInteractor: MovieUseCase) : ViewModel() {

    // get Movie with SearchQuery
    private val searchQuery = MutableStateFlow("")

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val pagingMovieList: Flow<PagingData<MovieDiscoverResult>> = searchQuery
        .debounce(400)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            movieInteractor.getPagerDiscoverMovies(query)
        }

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }

    // Get Genre ID and Name
    private val _movieGenreList: MutableLiveData<MovieGenreList> = MutableLiveData()
    val movieGenreList: LiveData<MovieGenreList> = _movieGenreList

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