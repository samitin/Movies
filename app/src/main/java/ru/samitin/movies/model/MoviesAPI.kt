package ru.samitin.movies.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import ru.samitin.movies.model.dto.MovieDTO

interface MoviesAPI {
    @GET("/3/movie/{id}")
    fun getMovie(
        @Path("id")id: Int,
        @Query("api_key") token: String,
        @Query("language") language: String
        ): Call<MovieDTO>
}