package com.example.hotelwallet.domain.usecase.commande_usecase

import com.example.hotelwallet.domain.model.Commande
import com.example.hotelwallet.domain.repository.AjouterCommandeRepository
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommandeUseCase @Inject constructor(
    private val ajouterCommandeRepository: AjouterCommandeRepository
) {
    suspend operator fun invoke(
        id_client: Int,
        prix_total: Int
    ): Flow<Resource<Commande>> =
        ajouterCommandeRepository.ajouterCommande(
            id_client,
            prix_total,
        )
}