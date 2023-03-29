package com.example.hotelwallet.domain.repository

import com.example.hotelwallet.domain.model.MenuItem
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    suspend fun getMenu(category: String): Flow<Resource<List<MenuItem>>>

}