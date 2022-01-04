package ru.samitin.movies.model

import ru.samitin.movies.entities.CardMovie

interface Repository {

    fun getLocalResource():MutableList<CardMovie>
    fun getRemoteResource():MutableList<CardMovie>
}