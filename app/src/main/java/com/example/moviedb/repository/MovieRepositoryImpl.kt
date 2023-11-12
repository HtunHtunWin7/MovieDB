package com.example.moviedb.repository

import com.example.moviedb.model.Movie
import com.example.moviedb.repository.dataSource.MovieLocalDataSource
import com.example.moviedb.repository.dataSource.MovieRemoteDataSource
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
) : MovieRepository {
    override fun getPopularMovies() =
        movieRemoteDataSource.getPopularMovies()

    override fun getMoviesFromDB(movieId: Int): Flow<Movie> =
        movieLocalDataSource.getMoviesFromDB(movieId)

    override suspend fun favorite(id: Int, favorite: Int) {
       movieLocalDataSource.favorite(id, favorite)
    }

    override suspend fun update(movie: Movie) {
        movieLocalDataSource.update(movie)
    }
}