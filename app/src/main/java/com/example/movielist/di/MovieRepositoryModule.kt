package com.example.movielist.di

import com.example.movielist.data.datasource.MovieDataSource
import com.example.movielist.data.pagingsource.MovieResultPagingSource
import com.example.movielist.data.repository.MovieRepository
import com.example.movielist.domain.repository.IMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieRepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(dataSource: MovieDataSource, pagingSource: MovieResultPagingSource): IMovieRepository {
        return MovieRepository(dataSource, pagingSource)
    }

}