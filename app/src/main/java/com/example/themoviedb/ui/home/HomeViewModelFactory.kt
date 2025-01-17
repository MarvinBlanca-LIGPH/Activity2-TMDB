package com.example.themoviedb.ui.home

import androidx.lifecycle.*

class HomeViewModelFactory(
    private val fragment: HomeScreenFragment
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java))
            return HomeScreenViewModel(fragment) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}