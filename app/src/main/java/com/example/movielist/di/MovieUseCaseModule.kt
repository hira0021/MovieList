package com.example.movielist.di

import com.example.movielist.domain.UseCase.MovieInteractor
import com.example.movielist.domain.UseCase.MovieUseCase
import com.example.movielist.domain.repository.IMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieUseCaseModule {

    @Singleton
    @Provides
    fun provideMovieUseCase(repository: IMovieRepository): MovieUseCase {
        return MovieInteractor(repository)
    }

}