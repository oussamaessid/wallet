package com.example.hotelwallet.presentation.gym


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelwallet.domain.model.*
import com.example.hotelwallet.domain.usecase.achat_usecase.AchatUseCase
import com.example.hotelwallet.domain.usecase.category_usecase.GetCategoryUseCase
import com.example.hotelwallet.domain.usecase.commande_usecase.CommandeUseCase
import com.example.hotelwallet.domain.usecase.gym_usecase.GetGymUseCase
import com.example.hotelwallet.domain.usecase.menu_item_usecase.GetMenuItemUseCase
import com.example.hotelwallet.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GymViewModel @Inject constructor(
    private val getGymUseCase: GetGymUseCase,
    private val achatUseCase: AchatUseCase
) : ViewModel() {

    private val _stateCategories = MutableLiveData<Resource<List<Gym>>>()
    val stateCategories: LiveData<Resource<List<Gym>>> get() = _stateCategories

    private val _stateAchat = MutableLiveData<Resource<Achat>>()
    val stateAchat: LiveData<Resource<Achat>> get() = _stateAchat

    fun getCategories(category: String) {
        viewModelScope.launch {
            getGymUseCase(category)
                .onEach {
                    _stateCategories.value = it
                }.launchIn(viewModelScope)
        }
    }

    fun getCommande(id_client: Int,id_plan: Int) {
        viewModelScope.launch {
            achatUseCase(id_client,id_plan)
                .onEach {
                    _stateAchat.value = it
                }.launchIn(viewModelScope)
        }
    }
}