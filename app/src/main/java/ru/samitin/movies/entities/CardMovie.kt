package ru.samitin.movies.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardMovie (
    val id:Long?,
    val name:String?,
    val date:String?,
    val rating:String?,
    val icon:String?
        ):Parcelable