package com.example.themoviedb.data.api

import com.example.themoviedb.data.model.MovieList
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @GET("popular")
    suspend fun getPopularMovies(@Query("page") page: Int = 1): Response<MovieList>

    @GET("top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int = 1): Response<MovieList>

    @GET("upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int = 1): Response<MovieList>
}