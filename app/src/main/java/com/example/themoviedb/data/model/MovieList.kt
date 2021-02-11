package com.example.themoviedb.data.model

data class MovieList(
    val page: Int,
    val results: List<MovieDetails>,
    val total_pages: Int,
    val total_results: Int
)
