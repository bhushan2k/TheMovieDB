package com.example.mvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm.models.Movie
import com.example.mvvm.models.MovieDetail
import com.example.mvvm.retrofit.MovieAPI
import com.example.mvvm.utils.Constants
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieAPI: MovieAPI) {

    private val  _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail>
        get() = _movieDetail

    suspend fun getMovies(){
        val result = movieAPI.getMovies(Constants.API_KEY)
        if (result.isSuccessful && result.body() != null) {
            _movies.postValue(result.body()!!.results)
        }
    }

    suspend fun getMovieDetails(id: String) {
        val result = movieAPI.getMovieDetails(id, Constants.API_KEY)
        if (result.isSuccessful && result.body() != null) {
            _movieDetail.postValue(result.body())
        }
    }
}