package ru.samitin.movies.model

import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.samitin.movies.BuildConfig
import ru.samitin.movies.model.dto.MovieDTO

class RemoteDataSource {
    private val movieAPI=Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        ).build().create(MoviesAPI::class.java)

    fun getMovieDescriptions(id:Int, callback:Callback<MovieDTO>, language:String="RU"){
        movieAPI.getMovie(id,BuildConfig.MOVIE_API_KEY,language).enqueue(callback)
    }
}