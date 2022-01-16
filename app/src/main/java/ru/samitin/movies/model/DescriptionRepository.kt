package ru.samitin.movies.model

import retrofit2.Callback
import ru.samitin.movies.model.dto.MovieDTO

interface DescriptionRepository {
    fun getMovieDescriptionsFromServer(
        id:Int,
        callback:Callback<MovieDTO>
    )
}