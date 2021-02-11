package com.example.themoviedb.data.api

import com.example.themoviedb.data.model.MovieDetails
import com.example.themoviedb.data.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("popular")
    suspend fun getPopularMovies(@Query("page") page: Int = 1): Response<MovieList>

    @GET("top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int = 1): Response<MovieList>

    @GET("upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int = 1): Response<MovieList>

    @GET("{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: Int): Response<MovieDetails>
}