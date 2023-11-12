package com.example.moviedb.di
import com.example.moviedb.db.MovieDao
import com.example.moviedb.repository.dataSource.MovieLocalDataSource
import com.example.moviedb.repository.dataSourceImpl.MovieLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    fun provideLocalDataSource(movieDao: MovieDao): MovieLocalDataSource =
        MovieLocalDataSourceImpl(movieDao = movieDao)
}