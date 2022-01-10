package ru.samitin.movies.model

import ru.samitin.movies.entities.CardMovie

interface Repository {

    fun getLocalResource():ArrayList<CardMovie>
    fun getRemoteResource():ArrayList<CardMovie>
}