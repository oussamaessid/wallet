package com.example.hotelwallet.data.source.remote

import com.example.hotelwallet.data.model.CategoryListResponse
import com.example.hotelwallet.data.model.GymListResponse
import com.example.hotelwallet.data.model.MenuItemListResponse
import com.example.hotelwallet.data.model.ServiceListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("services")
    suspend fun getServices(): ServiceListResponse

    @GET("menu/{id}")
    suspend fun getCategories(
        @Path("id") categoryId: String
    ): CategoryListResponse

    @GET("plats/{id}")
    suspend fun getMenuItems(
        @Path("id") id: String
    ): MenuItemListResponse

    @GET("plans/{id}")
    suspend fun getGym(
        @Path("id") id: String
    ): GymListResponse

}