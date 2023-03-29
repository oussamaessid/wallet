package com.example.hotelwallet.data.model


data class ServiceListResponse(
    val status: Int,
    val message: List<ServiceDto>
)