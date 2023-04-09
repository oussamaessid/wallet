package com.example.hotelwallet.domain.repository

import com.example.hotelwallet.domain.model.Category
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(category: String): Flow<Resource<List<Category>>>
}