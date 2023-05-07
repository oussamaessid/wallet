package com.example.hotelwallet.domain.usecase.login_usecase


import com.example.hotelwallet.domain.model.User
import com.example.hotelwallet.domain.repository.AppDataStore
import com.example.hotelwallet.domain.repository.LoginRepository
import com.example.hotelwallet.utility.KEY_TOKEN
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository,
    private val dataStore: AppDataStore
) {
    suspend operator fun invoke(userName: String, password: String): Flow<Resource<User>> {
        return repository.getLogin(userName, password)
//            .onEach {
//            if (it is Resource.Success<User>) {
//                it.data.token?.let { token -> dataStore.saveStringValue(KEY_TOKEN, token) }
//            }
//        }
    }
}