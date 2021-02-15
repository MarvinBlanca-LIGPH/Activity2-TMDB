package com.example.themoviedb.data.repository

import com.example.themoviedb.data.api.RetrofitBuilder
import com.example.themoviedb.data.model.MovieList
import retrofit2.Response

class HomeScreenRepository {
    suspend fun getMovies(filter: String?, page: Int): Response<MovieList> {
        return when (filter) {
            "Popular" -> RetrofitBuilder.getMovieList().getPopularMovies(page)
            "Top Rated" -> RetrofitBuilder.getMovieList().getTopRatedMovies(page)
            else -> RetrofitBuilder.getMovieList().getUpcomingMovies(page)
        }
    }
}