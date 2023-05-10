package com.example.hotelwallet.presentation.sign

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelwallet.domain.model.Message
import com.example.hotelwallet.domain.model.User
import com.example.hotelwallet.domain.usecase.authentication.login_usecase.LoginUseCase
import com.example.hotelwallet.domain.usecase.authentication.logout_use_case.LogoutUseCase
import com.example.hotelwallet.domain.usecase.authentication.register_use_case.SignUpUseCase
import com.example.hotelwallet.utility.Resource
import com.example.hotelwallet.utility.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val logoutUseCase: LogoutUseCase
    ) : ViewModel() {

    private val _stateLogin = SingleLiveEvent<Resource<User>>()
    val stateLogin: LiveData<Resource<User>> get() = _stateLogin

    private val _stateSignUp = MutableLiveData<Resource<Message>>()
    val stateSignUp: LiveData<Resource<Message>> get() = _stateSignUp

    private val _stateLogout = SingleLiveEvent<Resource<Boolean>>()
    val stateLogout: LiveData<Resource<Boolean>> get() = _stateLogout

    fun getLogin(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(email, password)
                .onEach {
                    _stateLogin.value = it
                }.launchIn(viewModelScope)
        }
    }

    fun signUp(
        name: String,
        email: String,
        password: String,
        image:String
    ) {
        viewModelScope.launch {
            signUpUseCase(
                name,
                email,
                password,
                image
            )
                .onEach {
                    _stateSignUp.value = it
                }.launchIn(viewModelScope)
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
                .onEach {
                    _stateLogout.value = it
                }.launchIn(viewModelScope)
        }
    }
}