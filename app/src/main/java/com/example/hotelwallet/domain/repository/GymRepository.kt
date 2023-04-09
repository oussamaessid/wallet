package com.example.hotelwallet.domain.repository

import com.example.hotelwallet.domain.model.Gym
import com.example.hotelwallet.domain.model.MenuItem
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow

interface GymRepository {
    suspend fun getGym(category: String): Flow<Resource<List<Gym>>>

}