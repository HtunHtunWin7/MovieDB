package com.example.moviedb.di

import com.example.moviedb.api.MovieApi
import com.example.moviedb.db.MovieDB
import com.example.moviedb.repository.dataSource.MovieRemoteDataSource
import com.example.moviedb.repository.dataSourceImpl.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideMoviesRemoteDataSource(movieApi: MovieApi, movieDB: MovieDB) : MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(movieApi, movieDB = movieDB)
}