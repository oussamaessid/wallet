package com.example.hotelwallet.data.source.remote

import com.example.hotelwallet.data.model.CategoryListResponse
import com.example.hotelwallet.data.model.MenuItemListResponse
import com.example.hotelwallet.data.model.ServiceListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HotelApi {

    @GET("categories.php")
    suspend fun getCategories(): CategoryListResponse

    @GET("filter.php")
    suspend fun getMenuItems(
        @Query("c") category: String
    ): MenuItemListResponse

}