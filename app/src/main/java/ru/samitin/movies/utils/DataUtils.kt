package ru.samitin.movies.utils

import ru.samitin.movies.entities.CardMovie
import ru.samitin.movies.model.dto.MovieDTO

fun convertDtoToModel(movieDTO: MovieDTO): List<CardMovie> {
    return listOf(CardMovie(movieDTO.id,movieDTO.title,movieDTO.release_date,movieDTO.popularity.toString(),movieDTO.poster_path))
}
