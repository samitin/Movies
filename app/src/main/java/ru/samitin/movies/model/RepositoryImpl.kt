package ru.samitin.movies.model

import ru.samitin.movies.R
import ru.samitin.movies.entities.CardMovie

class RepositoryImpl : Repository {
    private var movies:ArrayList<CardMovie>?
    init {
        movies=ArrayList<CardMovie>()
            movies?.add(CardMovie("Космический джем: Новое поколение", "08 июль 2021", "78", 0))
            movies?.add(CardMovie("Чёрная вдова", "07 июл 2021", "79", 1 ))
            movies?.add(CardMovie("Судная ночь навсегда", "30 июн 2021", "78", 2))
            movies?.add(CardMovie("Босс-молокосос 2", "01 июл 2021", "79", 3))
            movies?.add(CardMovie("Война будущего", "30 июн 2021", "82", 4))

    }
    override fun getLocalResource():ArrayList<CardMovie> = movies!!

    override fun getRemoteResource(): ArrayList<CardMovie> = movies!!
}