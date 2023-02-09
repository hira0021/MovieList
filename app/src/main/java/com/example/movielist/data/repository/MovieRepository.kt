package com.example.movielist.data.repository

import androidx.paging.*
import com.example.movielist.data.datasource.MovieDataSource
import com.example.movielist.data.pagingsource.MovieResultPagingSource
import com.example.movielist.domain.entity.*
import com.example.movielist.domain.repository.IMovieRepository
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    val movieDataSource: MovieDataSource,
    val movieResultPagingSource: MovieResultPagingSource
) :
    IMovieRepository {

    override suspend fun getDiscoverMovies(page: Int): Flow<DataState<MovieDiscover>> {
        return movieDataSource.getDiscoverMoviesFromDataSource(page)
    }

    override suspend fun getGenreList(): Flow<MovieGenreList> {
        return movieDataSource.getGenreListFromDataSource()
    }

    override suspend fun getMovieDetail(movieId: Int): Flow<DataState<MovieDetail>> {
        return movieDataSource.getMovieDetailFromDataSource(movieId)
    }

    override suspend fun getMovieCredits(movieId: Int): Flow<DataState<MovieCredits>> {
        return movieDataSource.getMovieCreditsFromDataSource(movieId)
    }

    override suspend fun getMovieReviews(movieId: Int): Flow<DataState<MovieReview>> {
        return movieDataSource.getMovieReviewFromDataSource(movieId)
    }
    //: Flow<PagingData<MovieDiscoverResult>>
    override fun getPagingDiscoverMovies(query: String): Flow<PagingData<MovieDiscoverResult>> {
        movieResultPagingSource.getSearchQuery(query)
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { movieResultPagingSource }
        ).flow
    }

    /*fun getPagingMovie() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { movieResultPagingSource }
    ).liveData*/

}