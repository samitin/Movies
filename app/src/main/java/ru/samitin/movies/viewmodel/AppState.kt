package ru.samitin.movies.viewmodel

import ru.samitin.movies.entities.CardMovie
import ru.samitin.movies.model.dto.MovieDTO

sealed class AppState{
    data class SuccessDescription(val movieDescription:MovieDTO) : AppState()
    data class Success(val movieListData:ArrayList<CardMovie>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
