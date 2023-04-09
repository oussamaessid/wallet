package com.example.hotelwallet.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class GymDto(
    val id: Int,
    val nom: String,
    val description: String,
    val prix: String,
    val image: String
)