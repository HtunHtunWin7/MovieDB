package com.example.moviedb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviedb.model.Movie
import com.example.moviedb.model.MovieRemoteKeys

@Database(
    entities = [Movie::class, MovieRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDB : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieRemoteKeysDao(): MovieRemoteKeysDao
}