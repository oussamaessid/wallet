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
        name: String,
        email: String,
        password: String,
        image: String
    ): Flow<Resource<Message>> =
        signRepository.signUp(
            name,
            email,
            password,
            image
        )
}