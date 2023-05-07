package com.example.hotelwallet.domain.repository

import com.example.hotelwallet.domain.model.Achat
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow

interface AjouterAchatRepository {
    suspend fun ajouterAchat(id_client: Int, id_plan: Int): Flow<Resource<Achat>>
}