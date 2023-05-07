package com.example.hotelwallet.domain.usecase.login_usecase

import com.example.hotelwallet.domain.model.Message
import com.example.hotelwallet.domain.model.User
import com.example.hotelwallet.domain.repository.LoginRepository
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signRepository: LoginRepository
) {
    suspend operator fun invoke(
        nom: String,
        prenom: String,
        email: String,
        password: String,
        id_hotel: String
    ): Flow<Resource<Message>> =
        signRepository.signUp(
            nom,
            prenom,
            email,
            password,
            id_hotel
        )
}