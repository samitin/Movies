package ru.samitin.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.samitin.movies.model.DescriptionRepository
import ru.samitin.movies.model.DescriptionRepositoryImpl
import ru.samitin.movies.model.RemoteDataSource
import ru.samitin.movies.model.dto.MovieDTO


private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class DescriptionViewModel(
    val descriptionLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val descriptionRepositoryImpl: DescriptionRepository = DescriptionRepositoryImpl(RemoteDataSource())
) : ViewModel() {

    fun getMovieFromRemoteSource(id:Int) {
        descriptionLiveData.value = AppState.Loading
        descriptionRepositoryImpl.getMovieDescriptionsFromServer(id, callBack)
    }

    private val callBack = object :
        Callback<MovieDTO> {

        override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
            val serverResponse: MovieDTO? = response.body()
            descriptionLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
            descriptionLiveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

        private fun checkResponse(serverResponse: MovieDTO): AppState {

            return if (serverResponse == null ) {
                AppState.Error(Throwable(CORRUPTED_DATA))
            } else {
                AppState.SuccessDescription(serverResponse)
            }
        }
    }

}