package ru.samitin.movies.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.samitin.movies.model.Repository
import ru.samitin.movies.model.RepositoryImpl

class HomeViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    val repositoryImpl: Repository = RepositoryImpl()) : ViewModel() {

    fun getLiveData() = liveDataToObserve
    fun getListMovie()=getDataFromLocalSource()
    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(10)
                liveDataToObserve.postValue(AppState.Success(repositoryImpl.getLocalResource()))
        }.start()
    }
}