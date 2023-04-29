package com.example.hotelwallet.domain.usecase.language_usecase

import android.media.MediaFormat.KEY_LANGUAGE
import com.example.hotelwallet.domain.repository.AppDataStore
import javax.inject.Inject

class SaveLanguageUseCase
@Inject constructor(
    private val appDataStore: AppDataStore
) {
    suspend operator fun invoke(language: String) {
        appDataStore.saveStringValue(KEY_LANGUAGE, language)
    }
}