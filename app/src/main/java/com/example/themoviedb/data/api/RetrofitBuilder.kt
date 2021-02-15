package com.example.themoviedb.data.api

import com.example.themoviedb.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val baseUrl = "https://api.themoviedb.org/3/movie/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val url = original.url()
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
            val requestBuilder = original.newBuilder()
                .url(url)
                .build()
            chain.proceed(requestBuilder)
        }.build()

    fun getMovieList(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiInterface::class.java)
    }

}