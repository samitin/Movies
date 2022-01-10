package ru.samitin.movies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.samitin.movies.R
import ru.samitin.movies.databinding.ActivityDescriptionBinding
import ru.samitin.movies.entities.CardMovie

const val DESCRIPTION_CARD_KEY="DESCRIPTION_CARD_KEY"

class DescriptionActivity : AppCompatActivity() {
    lateinit var binding:ActivityDescriptionBinding
    private var card:CardMovie?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        card=intent.extras?.getParcelable(DESCRIPTION_CARD_KEY)
        init()
    }

    private fun init(){
        binding.nameDescription.text=card?.name
        binding.butget.text="$10000"
        binding.date.text=card?.date
        binding.description.text="Таким образом можно передавать данные через интент между активити. " +
                "В активити-источнике просто добавляем данные в интент, а в активити-приёмнике получаем эти " +
                "данные из интента. Но в этом случае мы просто передали строку. Теперь создадим сложный объект, " +
                "будем его передавать в целевую активити и возвращать обратно в главную активити."
        binding.imageDescription.setImageResource(R.drawable.movie_icon)
        binding.janr.text="какой то там жанр"
        binding.rating.text=card?.rating
        binding.time.text="01.01.01"
    }

}