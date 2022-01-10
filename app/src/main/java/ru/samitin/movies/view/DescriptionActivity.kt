package ru.samitin.movies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.samitin.movies.R
import ru.samitin.movies.databinding.ActivityDescriptionBinding
import ru.samitin.movies.entities.CardMovie
import ru.samitin.movies.model.MovieLoader
import ru.samitin.movies.model.dto.Genre
import ru.samitin.movies.model.dto.MovieDTO
import java.lang.StringBuilder

const val DESCRIPTION_CARD_KEY="DESCRIPTION_CARD_KEY"

class DescriptionActivity : AppCompatActivity() {
    lateinit var binding:ActivityDescriptionBinding
    private var movieId:Int=0
    private val onLoadLister:MovieLoader.MovieLoaderListener=
        object : MovieLoader.MovieLoaderListener{
            override fun onLoaded(movieDTO: MovieDTO) {
                init(movieDTO)
            }

            override fun onFailed(throwable: Throwable) {

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieId= intent?.extras?.getInt(DESCRIPTION_CARD_KEY,550)!!
        val loader=MovieLoader(onLoadLister,movieId)
        loader.loadMovie()
    }

    private fun init(movieDTO: MovieDTO){
        binding.nameDescription.text=movieDTO.title
        binding.butget.text="$ ${movieDTO.budget}"
        binding.date.text=movieDTO.release_date
        binding.description.text=movieDTO.overview
        binding.imageDescription.setImageResource(R.drawable.movie_icon)
        var genre:String=""
        for (g in movieDTO.genres)
            genre+= "${g.name},"
        binding.janr.text=genre
        binding.rating.text="rating %${movieDTO.popularity}"
        binding.time.text="время ${movieDTO.runtime}min"
    }

}