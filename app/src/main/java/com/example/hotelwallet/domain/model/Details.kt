package com.example.hotelwallet.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class Details (
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val price: String,
    val quantity: String
)