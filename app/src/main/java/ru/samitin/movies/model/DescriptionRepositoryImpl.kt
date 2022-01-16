package ru.samitin.movies.model

import retrofit2.Callback
import ru.samitin.movies.model.dto.MovieDTO

class DescriptionRepositoryImpl (private val remoteDataSource: RemoteDataSource): DescriptionRepository {
    override fun getMovieDescriptionsFromServer(id: Int, callback: Callback<MovieDTO>) {
        remoteDataSource.getMovieDescriptions(id,callback)
    }
}