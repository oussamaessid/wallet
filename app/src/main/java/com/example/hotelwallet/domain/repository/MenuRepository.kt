package com.example.hotelwallet.domain.repository

import com.example.hotelwallet.domain.model.Menu
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    suspend fun getMenuByServiceId(serviceId: Int): Flow<Resource<List<Menu>>>
}