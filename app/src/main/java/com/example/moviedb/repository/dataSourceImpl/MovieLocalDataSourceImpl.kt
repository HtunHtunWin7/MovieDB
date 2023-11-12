package com.example.moviedb.repository.dataSourceImpl

import com.example.moviedb.db.MovieDao
import com.example.moviedb.model.Movie
import com.example.moviedb.repository.dataSource.MovieLocalDataSource
import kotlinx.coroutines.flow.Flow


class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {
    override fun getMoviesFromDB(movieId: Int): Flow<Movie> = movieDao.getMovie(movieId)
    override suspend fun favorite(id: Int, favorite: Int) {
        movieDao.favorite(id, favorite)
    }

    override suspend fun update(movie: Movie) {
        movieDao.update(movie)
    }

}