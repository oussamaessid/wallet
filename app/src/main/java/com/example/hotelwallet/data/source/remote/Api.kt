package com.example.hotelwallet.data.source.remote

import com.example.hotelwallet.data.model.*
import com.example.hotelwallet.domain.model.LoginRequest
import com.example.hotelwallet.domain.model.SignUp
import retrofit2.http.*

interface Api {

    @GET("services/{hotel_id}")
    suspend fun getServicesByHotel(
        @Path("hotel_id") hotelId: Int
    ): ServiceListResponse

    @GET("menu/{service_id}")
    suspend fun getMenuByServiceId(
        @Path("service_id") serviceId: Int
    ): MenuListResponse

    @GET("plats/{id}")
    suspend fun getMenuItems(
        @Path("id") id: String
    ): MenuItemListResponse

    @GET("plans/{id}")
    suspend fun getGym(
        @Path("id") id: String
    ): GymListResponse


    @POST("auth/login")
    suspend fun login(@Body credentials: LoginRequest):  UserResponse

    @POST("auth/createUser")
    suspend fun signUp(@Body credentials: SignUp): MessageDto

    @GET("auth/profile")
    suspend fun getProfile(
        @Header("Authorization") accessToken: String
    ):  UserDto

    @POST("auth/logout")
    suspend fun logout(
        @Header("Authorization") accessToken: String
    ):  MessageDto

}