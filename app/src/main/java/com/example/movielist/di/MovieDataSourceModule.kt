package com.example.movielist.di

import com.example.movielist.data.datasource.MovieDataSource
import com.example.movielist.data.local.MovieFavoriteListCacheMapper
import com.example.movielist.data.local.dao.movieFavoriteListDao
import com.example.movielist.data.pagingsource.MovieResultPagingSource
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
    fun provideMovieDataSource(retrofit: MovieService, movieFavoriteListDao: movieFavoriteListDao, movieFavoriteListCacheMapper: MovieFavoriteListCacheMapper): MovieDataSource {
        return MovieDataSource(retrofit, movieFavoriteListDao, movieFavoriteListCacheMapper)
    }

    @Singleton
    @Provides
    fun provideMovieResultPagingSource(retrofit: MovieService): MovieResultPagingSource {
        return MovieResultPagingSource(retrofit)
    }

}