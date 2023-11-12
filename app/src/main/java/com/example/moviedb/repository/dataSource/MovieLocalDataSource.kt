package com.example.moviedb.repository.dataSource
import com.example.moviedb.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getMoviesFromDB(movieId : Int): Flow<Movie>
    suspend fun favorite(id: Int, favorite: Int)
    suspend fun update(movie: Movie)
}