package com.example.moviedb.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.moviedb.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies: List<Movie>)

    @Query("SELECT * FROM movie")
    fun getAllMovies(): PagingSource<Int, Movie>

    @Query("SELECT * FROM movie WHERE id= :movieId")
    fun getMovie(movieId: Int): Flow<Movie>

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovies()

    @Query("UPDATE movie SET favorite=:favorite WHERE id = :id")
    suspend fun favorite(id: Int, favorite: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(movie: Movie)
}