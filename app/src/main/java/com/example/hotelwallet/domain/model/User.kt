package com.example.hotelwallet.domain.model

data class User(
    val id: Int,
    val nom:String,
    val prenom:String,
    val email:String,
    val id_hotel: String,
    val statut: String,
    val solde:String,
    val photo:String
)