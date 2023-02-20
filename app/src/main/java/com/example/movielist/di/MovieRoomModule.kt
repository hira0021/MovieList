package com.example.movielist.di

import android.content.Context
import androidx.room.Room
import com.example.movielist.data.local.MovieDatabase
import com.example.movielist.data.local.dao.MovieFavoriteListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieRoomModule {

    @Singleton
    @Provides
    fun provideMovieDb(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            MovieDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(movieDatabase: MovieDatabase): MovieFavoriteListDao {
        return movieDatabase.movieFavoriteListDao()
    }
}