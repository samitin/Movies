package ru.samitin.movies.model

import ru.samitin.movies.R
import ru.samitin.movies.entities.CardMovie

class RepositoryImpl : Repository {
    private var movies:MutableList<CardMovie>?
    init {
        movies=mutableListOf<CardMovie>(
            CardMovie("Космический джем: Новое поколение", "08 июль 2021", "78", R.drawable.image_space_jam_a_new_legacy),
            CardMovie("Чёрная вдова", "07 июл 2021", "79", R.drawable.black_widow ),
            CardMovie("Судная ночь навсегда", "30 июн 2021", "78", R.drawable.the_forever_purge),
            CardMovie("Босс-молокосос 2", "01 июл 2021", "79", R.drawable.the_boss_baby),
            CardMovie("Война будущего", "30 июн 2021", "82", R.drawable.the_tomorrow_war)
        )
    }
    override fun getLocalResource():MutableList<CardMovie> = movies!!

    override fun getRemoteResource(): MutableList<CardMovie> = movies!!
}