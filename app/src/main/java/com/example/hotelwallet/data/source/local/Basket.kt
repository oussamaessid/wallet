package com.example.hotelwallet.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data  class Basket (
    @PrimaryKey
    val name: String,
    val price: String,
    val quantity: String,
    val image: String,
)