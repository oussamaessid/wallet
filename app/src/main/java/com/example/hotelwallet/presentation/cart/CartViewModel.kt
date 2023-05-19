package com.example.hotelwallet.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelwallet.domain.model.SubMenu
import com.example.hotelwallet.domain.usecase.cart_usecase.GetCartProductUseCase
import com.example.hotelwallet.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getProductUseCase: GetCartProductUseCase
) : ViewModel() {

    private val _stateProductList = MutableLiveData<Resource<List<SubMenu>>>()
    val stateProductList: LiveData<Resource<List<SubMenu>>> get() = _stateProductList

    fun getProductList() {
        viewModelScope.launch {
            getProductUseCase()
                .onEach {
                    _stateProductList.value = it
                }.launchIn(viewModelScope)
        }
    }
}