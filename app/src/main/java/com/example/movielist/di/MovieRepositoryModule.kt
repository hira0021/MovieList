package com.example.movielist.di

import com.example.movielist.data.datasource.IMovieDataSource
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
    fun provideMovieRepository(dataSource: IMovieDataSource): IMovieRepository {
        return MovieRepository(dataSource)
    }

}