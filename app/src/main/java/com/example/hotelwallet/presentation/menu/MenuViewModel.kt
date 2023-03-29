package com.example.hotelwallet.presentation.menu


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelwallet.domain.model.Category
import com.example.hotelwallet.domain.model.MenuItem
import com.example.hotelwallet.domain.usecase.category_usecase.GetCategoryUseCase
import com.example.hotelwallet.domain.usecase.menu_item_usecase.GetMenuItemUseCase
import com.example.hotelwallet.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
) : ViewModel() {

    private val _stateCategories = MutableLiveData<Resource<List<Category>>>()
    val stateCategories: LiveData<Resource<List<Category>>> get() = _stateCategories

    fun getCategories() {
        viewModelScope.launch {
            getCategoryUseCase()
                .onEach {
                    _stateCategories.value = it
                }.launchIn(viewModelScope)
        }
    }

}