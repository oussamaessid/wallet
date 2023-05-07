package com.example.hotelwallet.presentation.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.hotelwallet.data.source.local.AppBasket
import com.example.hotelwallet.data.source.local.Favorite
import com.example.hotelwallet.domain.model.Category
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    val favoriteDao = Room.databaseBuilder(
        application,
        AppBasket::class.java, "app-database"
    ).build()

    val getallFavorites: LiveData<List<Favorite>> = favoriteDao.favoritesDao().getFavorites()

    private val _stateFavorites = MutableLiveData<Resource<List<Favorite>>>()
    val stateFavorites: LiveData<Resource<List<Favorite>>> get() = _stateFavorites

    fun addFavorite(favorite: Favorite) {
        viewModelScope.launch {
            favoriteDao.favoritesDao().addFavorite(favorite)
        }
    }

    fun deleteFavorites(favorite: Favorite) {
        viewModelScope.launch {
            favoriteDao.favoritesDao().deleteFavorite(favorite)
        }
    }
    fun isMealSavedInDatabase(name: String): Boolean {
        var meal: Favorite? = null
        runBlocking(Dispatchers.IO) {
            meal = favoriteDao.favoritesDao().getMealById(name)
        }
        if (meal == null)
            return false
        return true

    }

    fun deleteMealById(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteDao.favoritesDao().deleteMealById(name)
        }
    }

}