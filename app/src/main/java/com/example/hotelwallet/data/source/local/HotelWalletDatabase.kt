package com.example.hotelwallet.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hotelwallet.data.model.FavoriteEntity
import com.example.hotelwallet.data.model.SubMenuDto
import com.example.hotelwallet.data.source.local.dao.FavoriteDao
import com.example.hotelwallet.data.source.local.dao.ProductDao

@Database(entities = [SubMenuDto::class, FavoriteEntity::class], version = 1)
abstract class HotelWalletDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        const val DATABASE_NAME = "hotel_wallet"
    }
}