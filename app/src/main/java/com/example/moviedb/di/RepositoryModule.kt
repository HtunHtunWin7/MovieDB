package com.example.moviedb.di

import com.example.moviedb.repository.MovieRepository
import com.example.moviedb.repository.MovieRepositoryImpl
import com.example.moviedb.repository.dataSource.MovieLocalDataSource
import com.example.moviedb.repository.dataSource.MovieRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMoviesRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource
    ): MovieRepository =
        MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource = movieLocalDataSource)
}