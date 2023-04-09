package com.example.hotelwallet.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Gym(
    val id: Int,
    val nom: String,
    val description: String,
    val prix: String,
    val image: String
)