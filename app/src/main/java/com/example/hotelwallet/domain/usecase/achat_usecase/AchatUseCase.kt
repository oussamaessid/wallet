package com.example.hotelwallet.domain.usecase.achat_usecase

import com.example.hotelwallet.domain.model.Achat
import com.example.hotelwallet.domain.repository.AjouterAchatRepository
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AchatUseCase @Inject constructor(
    private val ajouterAchatRepository: AjouterAchatRepository
) {
    suspend operator fun invoke(
        id_client: Int,
        id_plan: Int
    ): Flow<Resource<Achat>> =
        ajouterAchatRepository.ajouterAchat(
            id_client,
            id_plan,
        )
}