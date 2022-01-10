package ru.samitin.movies.model

import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import ru.samitin.movies.BuildConfig
import ru.samitin.movies.model.dto.MovieDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MovieLoader(private val listener:MovieLoaderListener,val id:Int) {

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadMovie(){
        try {
            val uri=URL("https://api.themoviedb.org/3/movie/$id?api_key=${BuildConfig.MOVIE_API_KEY}")
            val handler = Handler()
            Thread{
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection=uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod="GET"
                    urlConnection.readTimeout=1000
                    val bufferedReader=BufferedReader(InputStreamReader(urlConnection.inputStream))

                    val movieDTO:MovieDTO = Gson().fromJson(getLines(bufferedReader),MovieDTO::class.java)
                    handler.post { listener.onLoaded(movieDTO) }
                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                    listener.onFailed(e)
                } finally {
                    urlConnection.disconnect()
                }
            }.start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
            listener.onFailed(e)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String{
        return reader.lines().collect(Collectors.joining("\n"))
    }
    interface MovieLoaderListener{
        fun onLoaded(movieDTO: MovieDTO)
        fun onFailed(throwable: Throwable)
    }
}