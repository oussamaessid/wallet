package com.example.hotelwallet.data.repository

import android.annotation.SuppressLint
import com.example.hotelwallet.data.mapper.LoginMapper
import com.example.hotelwallet.data.mapper.MessageMapper
import com.example.hotelwallet.data.source.remote.Api
import com.example.hotelwallet.domain.model.Login
import com.example.hotelwallet.domain.model.Message
import com.example.hotelwallet.domain.model.SignUp
import com.example.hotelwallet.domain.model.User
import com.example.hotelwallet.domain.repository.LoginRepository
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: Api,
    private val loginMapper: LoginMapper,
    private val messageMapper: MessageMapper
) : LoginRepository {

    @SuppressLint("SuspiciousIndentation")
    override suspend fun getLogin(email: String, password: String): Flow<Resource<User>> = flow {
        val loginRequest = Login(email, password)

        try {
            emit(Resource.Loading)
            val response = loginMapper.map(
                api.login(loginRequest).user
            )
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String,
        image: String
    ): Flow<Resource<Message>> = flow {
        val registerRequest = SignUp(name, email, password,image)

        try {
            emit(Resource.Loading)
            val response = messageMapper.map(
                api.signUp(registerRequest)
            )
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}
