package com.example.hotelwallet.data.source.local.dao

import androidx.room.*
import com.example.hotelwallet.data.model.SubMenuDto

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: SubMenuDto)

    @Delete
    suspend fun deleteProduct(product: SubMenuDto)

    @Query("SELECT * FROM SubMenuDto")
    fun getAllProducts(): List<SubMenuDto>
}
