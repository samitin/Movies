package ru.samitin.movies.model

import ru.samitin.movies.R
import ru.samitin.movies.entities.CardMovie

class RepositoryImpl : Repository {
    private var movies:ArrayList<CardMovie>?
    init {
        movies=ArrayList<CardMovie>()
            movies?.add(CardMovie(550,"Космический джем: Новое поколение", "08 июль 2021", "78", ""))
            movies?.add(CardMovie(550,"Чёрная вдова", "07 июл 2021", "79", "" ))
            movies?.add(CardMovie(550,"Судная ночь навсегда", "30 июн 2021", "78", ""))
            movies?.add(CardMovie(550,"Босс-молокосос 2", "01 июл 2021", "79", ""))
            movies?.add(CardMovie(550,"Война будущего", "30 июн 2021", "82", ""))

    }
    override fun getLocalResource():ArrayList<CardMovie> = movies!!

    override fun getRemoteResource(): ArrayList<CardMovie> = movies!!
}