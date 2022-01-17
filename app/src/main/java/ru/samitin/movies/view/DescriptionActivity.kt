package ru.samitin.movies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import ru.samitin.movies.BuildConfig
import ru.samitin.movies.R
import ru.samitin.movies.databinding.ActivityDescriptionBinding
import ru.samitin.movies.entities.CardMovie
import ru.samitin.movies.model.MovieLoader
import ru.samitin.movies.model.dto.Genre
import ru.samitin.movies.model.dto.MovieDTO
import ru.samitin.movies.utils.showSnackBar
import ru.samitin.movies.viewmodel.AppState
import ru.samitin.movies.viewmodel.DescriptionViewModel
import java.lang.StringBuilder

const val DESCRIPTION_CARD_KEY="DESCRIPTION_CARD_KEY"

class DescriptionActivity : AppCompatActivity() {
    lateinit var binding:ActivityDescriptionBinding
    private var movieId:Int=0
    private val viewModel:DescriptionViewModel by lazy { ViewModelProvider(this).get(DescriptionViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieId= intent?.extras?.getInt(DESCRIPTION_CARD_KEY,550)!!
        viewModel.descriptionLiveData.observe(this,{
            var app=it
            renderData(appState = it)})
        viewModel.getMovieFromRemoteSource(movieId)
    }

    private fun renderData(appState: AppState){
        when(appState){
            is AppState.SuccessDescription ->{
                binding.mainView.visibility=View.VISIBLE
                binding.loadingLayout.visibility=View.GONE
                init(appState.movieDescription)
            }
            is AppState.Loading ->{
                binding.mainView.visibility=View.GONE
                binding.loadingLayout.visibility=View.VISIBLE
            }
            is AppState.Error ->{
                binding.mainView.visibility=View.GONE
                binding.loadingLayout.visibility=View.VISIBLE
                binding.mainView.showSnackBar("ошибка","перезагрузка",{
                    viewModel.getMovieFromRemoteSource(movieId)
                })
            }
        }
    }
    private fun init(movieDTO: MovieDTO){
        binding.nameDescription.text=movieDTO.title
        binding.butget.text="$ ${movieDTO.budget}"
        binding.date.text=movieDTO.release_date
        binding.description.text=movieDTO.overview
        Picasso.get()
            .load("https://www.themoviedb.org/t/p/w300_and_h450_bestv2/${movieDTO.poster_path}")
            .error(R.drawable.ic_launcher_background)
            .into(binding.imageDescription)
        var genre:String=""
        for (g in movieDTO.genres)
            genre+= "${g.name},"
        binding.janr.text=genre
        binding.rating.text="rating %${movieDTO.popularity}"
        binding.time.text="время ${movieDTO.runtime}min"
    }

}