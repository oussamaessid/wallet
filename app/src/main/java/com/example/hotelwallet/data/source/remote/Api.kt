package com.example.hotelwallet.data.source.remote

import com.example.hotelwallet.data.model.ServiceListResponse
import retrofit2.http.GET

interface Api {

    @GET("services")
    suspend fun getServices(): ServiceListResponse
}