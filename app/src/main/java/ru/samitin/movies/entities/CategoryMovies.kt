package ru.samitin.movies.entities

data class CategoryMovies (
    val categoryName:String,
    val listMovies:ArrayList<CardMovie>
)