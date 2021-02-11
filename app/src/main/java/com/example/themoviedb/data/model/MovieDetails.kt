package com.example.themoviedb.data.model

data class MovieDetails(
    val adult: Boolean,
    val backdrop_path: String,
    val poster_path: String,
    val id: Int,
    val overview: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
)