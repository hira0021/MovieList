package com.example.movielist.di

import com.example.movielist.data.datasource.IMovieDataSource
import com.example.movielist.data.datasource.MovieDataSource
import com.example.movielist.data.remote.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDataSourceModule {

    @Singleton
    @Provides
    fun provideMovieDataSource(retrofit: MovieService): IMovieDataSource {
        return MovieDataSource(retrofit)
    }

}