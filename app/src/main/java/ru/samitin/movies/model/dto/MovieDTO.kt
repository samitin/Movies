package ru.samitin.movies.model.dto

data class MovieDTO (

    val original_title: String,
    val genres: List<Genre>,
    val runtime: Long,
    val budget: Long,
    val revenue: Long,
    val backdrop_path: String,
    val release_date: String,
    val overview: String,
    val id: Long,
    val original_language: String,
    val popularity: Double,
    val title: String,
    val poster_path: String,
    )

data class Genre (
    val id: Long,
    val name: String
)

