package com.example.hotelwallet.presentation.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelwallet.domain.model.Services
import com.example.hotelwallet.domain.usecase.services_usecase.GetServicesUseCase
import com.example.hotelwallet.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(
    private val getServicesUseCase: GetServicesUseCase,
) : ViewModel() {

    private val _stateServices = MutableLiveData<Resource<List<Services>>>()
    val stateServices: LiveData<Resource<List<Services>>> get() = _stateServices

    fun getServices() {
        viewModelScope.launch {
            getServicesUseCase()
                .onEach {
                    _stateServices.value = it
                }.launchIn(viewModelScope)
        }
    }

}