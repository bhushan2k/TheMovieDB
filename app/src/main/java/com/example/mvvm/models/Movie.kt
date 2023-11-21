package com.example.mvvm.models

data class MoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val total_results: Int,
    val total_pages: Int
)

data class Movie(
    val poster_path: String,
    val backdrop_path: String,
    val title: String,
    val id: Int
)