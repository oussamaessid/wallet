package com.example.hotelwallet.domain.usecase.services_usecase

import com.example.hotelwallet.domain.model.Services
import com.example.hotelwallet.domain.repository.ServiceRepository
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetServicesUseCase @Inject constructor(
    private val repository: ServiceRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Services>>> = repository.getServices()
}