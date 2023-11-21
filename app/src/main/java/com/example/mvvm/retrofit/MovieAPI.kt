package com.example.mvvm.retrofit

import com.example.mvvm.models.MovieDetail
import com.example.mvvm.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") apiKey: String) : Response<MoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: String, @Query("api_key") apiKey: String?) : Response<MovieDetail>
}