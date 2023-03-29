package com.example.hotelwallet.domain.usecase.category_usecase


import com.example.hotelwallet.domain.model.Category
import com.example.hotelwallet.domain.repository.CategoryRepository
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Category>>> = repository.getCategories()
}