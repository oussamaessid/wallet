package com.example.hotelwallet.domain.usecase.menu_item_usecase

import com.example.hotelwallet.domain.model.MenuItem
import com.example.hotelwallet.domain.repository.MenuRepository
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMenuItemUseCase @Inject constructor(
    private val repository: MenuRepository
) {
    suspend operator fun invoke(category: String): Flow<Resource<List<MenuItem>>> =
        repository.getMenu(category)
}