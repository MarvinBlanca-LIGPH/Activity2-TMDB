package com.example.themoviedb.ui.home

import androidx.lifecycle.*
import com.example.themoviedb.R
import com.example.themoviedb.data.model.MovieList
import com.example.themoviedb.data.repository.HomeScreenRepository
import com.example.themoviedb.util.Resource
import kotlinx.coroutines.*
import com.example.themoviedb.ui.home.HomeScreenAdapter.Companion.itemClicked
import com.example.themoviedb.util.FirebaseSingleton
import com.example.themoviedb.util.NavComponentUtil.navigate

class HomeScreenViewModel(
    private val fragment: HomeScreenFragment
) : ViewModel() {
    private val repository = HomeScreenRepository()
    val resource = MutableLiveData<Resource<MovieList>>()
    var filter = "Popular"
    var page = 1
    var lastPage = Int.MAX_VALUE

    init {
        FirebaseSingleton.getToken()
        onItemClicked()
    }

    fun callMovies() {
        if (page <= lastPage) {
            resource.value = Resource.loading(data = null)

            viewModelScope.launch(Dispatchers.IO) {
                val response = repository.getMovies(filter, page)

                viewModelScope.launch {
                    if (response.isSuccessful) {
                        response.body()?.let { data ->
                            resource.value = Resource.success(data = data)
                        }
                    } else {
                        resource.value =
                            Resource.error(
                                data = null,
                                message = response.message() ?: "Unknown Error"
                            )
                    }
                }
            }
        }
    }

    private fun onItemClicked() {
        itemClicked = { movieDetails ->
            page = 1
            fragment.navigate(
                R.id.next_action,
                "poster" to movieDetails.poster_path,
                "backdrop" to movieDetails.backdrop_path,
                "title" to movieDetails.title,
                "releaseDate" to movieDetails.release_date,
                "overview" to movieDetails.overview,
                "ratings" to movieDetails.vote_average
            )
        }
    }
}