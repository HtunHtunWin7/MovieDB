package com.example.moviedb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.moviedb.model.Movie
import com.example.moviedb.repository.MovieRepository
import com.example.moviedb.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

    private val _movie by lazy {
        MutableLiveData<ViewState<PagingData<Movie>>>()
    }
    val movie: LiveData<ViewState<PagingData<Movie>>>
        get() = _movie

    private val _movieById by lazy {
        MutableLiveData<ViewState<Movie>>()
    }
    val movieById: LiveData<ViewState<Movie>>
        get() = _movieById

    fun getMovies() = viewModelScope.launch {
        repository.getPopularMovies()
            .onStart { _movie.postValue(ViewState.Loading()) }
            .catch { _movie.postValue(ViewState.Error(it.localizedMessage)) }
            .collect { _movie.postValue(ViewState.Success(it)) }
    }

    fun getMovieById(id: Int) = viewModelScope.launch {
        repository.getMoviesFromDB(id)
            .onStart { _movieById.postValue(ViewState.Loading()) }
            .catch { _movieById.postValue(ViewState.Error(it.localizedMessage)) }
            .collect { _movieById.postValue(ViewState.Success(it)) }
    }


    fun updateMovie(movie: Movie) = viewModelScope.launch {
        repository.update(movie)
    }
}