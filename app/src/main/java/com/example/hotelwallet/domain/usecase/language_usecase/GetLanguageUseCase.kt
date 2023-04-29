package com.example.hotelwallet.domain.usecase.language_usecase

import android.media.MediaFormat.KEY_LANGUAGE
import com.example.hotelwallet.domain.repository.AppDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLanguageUseCase
@Inject constructor(
    private val appDataStore: AppDataStore
) {
    suspend operator fun invoke(): Flow<String> = appDataStore.getStringValue(KEY_LANGUAGE)
}