package com.example.hotelwallet.domain.repository

import com.example.hotelwallet.domain.model.Message
import com.example.hotelwallet.domain.model.User
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun getLogin(email: String, password: String): Flow<Resource<User>>

    suspend fun signUp(
        nom: String,
        prenom: String,
        email: String,
        password: String,
        id_hotel: String
    ): Flow<Resource<Message>>

}