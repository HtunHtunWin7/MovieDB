package com.example.moviedb.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie_remote_keys")
data class MovieRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?,
)
