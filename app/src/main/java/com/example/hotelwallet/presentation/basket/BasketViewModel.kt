package com.example.hotelwallet.presentation.basket

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.hotelwallet.data.source.local.AppBasket
import com.example.hotelwallet.data.source.local.Basket
import com.example.hotelwallet.data.source.local.Favorite
import com.example.hotelwallet.data.source.local.FavoritesDao
import com.example.hotelwallet.domain.model.Category
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BasketViewModel(application: Application) : AndroidViewModel(application) {
    val favoriteDao = Room.databaseBuilder(
        application,
        AppBasket::class.java, "app-database"
    ).build()


    val allFavorites: LiveData<List<Basket>> = favoriteDao.favoriteDao().getAllFavorites()

    fun insertFavorite(favorite: Basket) {
        viewModelScope.launch {
            favoriteDao.favoriteDao().insertFavorite(favorite)
        }
    }

    fun deleteFavorite(favorite: Basket) {
        viewModelScope.launch {
            favoriteDao.favoriteDao().deleteFavorite(favorite)
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            favoriteDao.favoriteDao().deleteAll()
        }
    }

}