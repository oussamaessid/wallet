package com.example.hotelwallet.presentation.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelwallet.domain.usecase.token_usecase.GetTokenUseCase
import com.example.hotelwallet.domain.usecase.token_usecase.SaveTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val saveLanguageUseCase: SaveTokenUseCase,
    private val getLanguageUseCase: GetTokenUseCase
) : ViewModel() {

    private val _stateLanguage = MutableStateFlow<String>("")
    val stateLanguage: SharedFlow<String> get() = _stateLanguage

    init {
        getLanguage()
    }

    private fun getLanguage() {
        viewModelScope.launch {
            getLanguageUseCase()
                .onEach {
                    _stateLanguage.emit(it)
                }.launchIn(viewModelScope)
        }
    }

    suspend fun saveLanguage(language: String) {
        viewModelScope.launch {
            saveLanguageUseCase(language)
        }
    }

}