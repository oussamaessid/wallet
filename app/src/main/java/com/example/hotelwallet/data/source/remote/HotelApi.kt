package com.example.hotelwallet.data.source.remote

import com.example.hotelwallet.data.model.MenuItemListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HotelApi {

    @GET("filter.php")
    suspend fun getMenuItems(
        @Query("c") category: String
    ): MenuItemListResponse

}