package com.example.hotelwallet.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BasketsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(basket: Basket)

    @Delete
    suspend fun deleteFavorite(basket: Basket)

    @Query("SELECT * FROM favorite_table")
    fun getAllFavorites(): LiveData<List<Basket>>
}
