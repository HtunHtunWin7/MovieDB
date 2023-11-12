package com.example.moviedb.repository.dataSource

import androidx.paging.PagingData
import com.example.moviedb.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
     fun getPopularMovies(): Flow<PagingData<Movie>>
}