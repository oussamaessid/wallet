package com.example.hotelwallet.presentation.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelwallet.domain.model.Commande
import com.example.hotelwallet.domain.model.MenuItem
import com.example.hotelwallet.domain.usecase.commande_usecase.CommandeUseCase
import com.example.hotelwallet.domain.usecase.menu_item_usecase.GetMenuItemUseCase
import com.example.hotelwallet.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommandeViewModel @Inject constructor(
    private val commandeUseCase: CommandeUseCase
) : ViewModel() {

    private val _stateCommande = MutableLiveData<Resource<Commande>>()
    val stateCommande: LiveData<Resource<Commande>> get() = _stateCommande


    fun getCommande(id_client: Int,prix_total: Int) {
        viewModelScope.launch {
            commandeUseCase(id_client,prix_total)
                .onEach {
                    _stateCommande.value = it
                }.launchIn(viewModelScope)
        }
    }
}