package com.example.movielist.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movielist.data.remote.MovieService
import com.example.movielist.domain.entity.MovieDiscoverResult
import com.example.movielist.util.Const
import javax.inject.Inject

class MovieResultPagingSource @Inject constructor(
    val apiService: MovieService
): PagingSource<Int, MovieDiscoverResult>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDiscoverResult>): Int? {
        //return null
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDiscoverResult> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiService.getDiscoverMovie(position, Const.API_KEY)
            val repos = response.movieDiscoverResults
            LoadResult.Page(
                data = repos,
                prevKey = if (position == 1) null else -1,
                nextKey = position.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}