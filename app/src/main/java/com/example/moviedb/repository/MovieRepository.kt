package com.example.moviedb.repository

import androidx.paging.PagingData
import com.example.moviedb.model.Movie
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    fun getPopularMovies(): Flow<PagingData<Movie>>
    fun getMoviesFromDB(movieId: Int): Flow<Movie>
    suspend fun favorite(id: Int, favorite: Int)
    suspend fun update(movie: Movie)
}