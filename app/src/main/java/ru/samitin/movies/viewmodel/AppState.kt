package ru.samitin.movies.viewmodel

import ru.samitin.movies.entities.CardMovie

sealed class AppState{
    data class Success(val movieListData:ArrayList<CardMovie>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
