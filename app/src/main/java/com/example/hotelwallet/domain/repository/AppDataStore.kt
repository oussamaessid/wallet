package com.example.hotelwallet.domain.repository

import kotlinx.coroutines.flow.Flow

interface AppDataStore {

    suspend fun saveStringValue(key: String, value: String)

    suspend fun getStringValue(key: String):Flow<String>

}