package com.example.hotelwallet.presentation.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelwallet.domain.model.MenuItem
import com.example.hotelwallet.domain.usecase.menu_item_usecase.GetMenuItemUseCase
import com.example.hotelwallet.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getFavoriteUseCase: GetMenuItemUseCase
) : ViewModel() {

    private val _stateFavorite = MutableLiveData<Resource<List<MenuItem>>>()
    val stateFavorite: LiveData<Resource<List<MenuItem>>> get() = _stateFavorite


    fun getFavorite(category: String) {
        viewModelScope.launch {
            getFavoriteUseCase(category)
                .onEach {
                    _stateFavorite.value = it
                }.launchIn(viewModelScope)
        }
    }
}