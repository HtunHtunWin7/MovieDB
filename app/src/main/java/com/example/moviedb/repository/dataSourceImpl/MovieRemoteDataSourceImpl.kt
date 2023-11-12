package com.example.moviedb.repository.dataSourceImpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviedb.api.MovieApi
import com.example.moviedb.db.MovieDB
import com.example.moviedb.model.Movie
import com.example.moviedb.paging.MovieRemoteMediator
import com.example.moviedb.repository.dataSource.MovieRemoteDataSource
import kotlinx.coroutines.flow.Flow


class MovieRemoteDataSourceImpl(private val movieApi: MovieApi, private val movieDB: MovieDB) :
    MovieRemoteDataSource {
    private val movieDao = movieDB.movieDao()

    @OptIn(ExperimentalPagingApi::class)
    override  fun getPopularMovies() : Flow<PagingData<Movie>> {
        val pagingSourceFactory = { movieDao.getAllMovies() }
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = MovieRemoteMediator(
                movieApi,
                movieDB
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }
}