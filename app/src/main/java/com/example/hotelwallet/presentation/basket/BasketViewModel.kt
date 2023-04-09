package com.example.hotelwallet.presentation.basket

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.hotelwallet.data.source.local.AppBasket
import com.example.hotelwallet.data.source.local.Basket
import kotlinx.coroutines.launch

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
}