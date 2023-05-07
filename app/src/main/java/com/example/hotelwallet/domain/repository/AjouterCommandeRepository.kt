package com.example.hotelwallet.domain.repository

import com.example.hotelwallet.domain.model.Commande
import com.example.hotelwallet.domain.model.MenuItem
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow

interface AjouterCommandeRepository {
    suspend fun ajouterCommande(id_client: Int, prix_total: Int): Flow<Resource<Commande>>
}