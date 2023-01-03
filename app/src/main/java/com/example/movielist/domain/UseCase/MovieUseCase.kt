package com.example.movielist.domain.UseCase

import com.example.movielist.domain.entity.DiscoverMovie
import com.example.movielist.domain.entity.Genre
import com.example.movielist.domain.entity.GenreList
import com.example.movielist.util.DataState
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    suspend fun getDiscoverMovie(): Flow<DataState<DiscoverMovie>>

    suspend fun getGenreList(): Flow<GenreList>

}