package com.example.hotelwallet.presentation.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelwallet.domain.model.Menu
import com.example.hotelwallet.domain.usecase.menu_usecase.GetMenuUseCase
import com.example.hotelwallet.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getMenuUseCase: GetMenuUseCase,
) : ViewModel() {

    private val _stateMenuList = MutableLiveData<Resource<List<Menu>>>()
    val stateMenuList: LiveData<Resource<List<Menu>>> get() = _stateMenuList

    fun getMenuList(serviceId: Int) {
        viewModelScope.launch {
            getMenuUseCase(serviceId)
                .onEach {
                    _stateMenuList.value = it
                }.launchIn(viewModelScope)
        }
    }

}