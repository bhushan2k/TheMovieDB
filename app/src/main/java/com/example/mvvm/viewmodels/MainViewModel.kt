package com.example.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.models.Movie
import com.example.mvvm.models.MovieDetail
import com.example.mvvm.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: MovieRepository) : ViewModel() {
    val moviesLiveData : LiveData<List<Movie>>
        get() = repository.movies

    val movieDetailLiveData: LiveData<MovieDetail>
        get() = repository.movieDetail

    init {
//        viewModelScope.launch {
//            repository.getMovies()
//        }
    }

    fun getMovies() {
        viewModelScope.launch {
            repository.getMovies()
        }
    }

    fun getMovieDetails(id: String) {
        viewModelScope.launch {
            repository.getMovieDetails(id)
        }
    }
}