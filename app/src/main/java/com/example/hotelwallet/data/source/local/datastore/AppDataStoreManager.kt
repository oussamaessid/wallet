package com.example.hotelwallet.data.source.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.hotelwallet.domain.repository.AppDataStore
import com.example.hotelwallet.utility.APP_DATASTORE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = APP_DATASTORE)

class AppDataStoreManager(private val context: Context): AppDataStore {

    override suspend fun saveStringValue(key: String, value: String) {
        val wrappedKey = stringPreferencesKey(key)
        context.datastore.edit {
            it[wrappedKey] = value
        }
    }

    override suspend fun getStringValue(key: String): Flow<String> {
        val wrappedKey = stringPreferencesKey(key)
        val valueFlow: Flow<String> = context.datastore.data.map {
            it[wrappedKey] ?: ""
        }
        return valueFlow
    }

}