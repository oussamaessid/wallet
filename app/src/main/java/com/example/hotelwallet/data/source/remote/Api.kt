package com.example.hotelwallet.data.source.remote

import com.example.hotelwallet.data.model.*
import com.example.hotelwallet.domain.model.*
import retrofit2.Response
import retrofit2.http.*

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

    @POST("auth/login")
    suspend fun login(@Body credentials: Login):  UserListResponse


    @POST("auth/create")
    suspend fun signUp(@Body credentials: SignUp): MessageDto

    @POST("ajouterCommande")
    suspend fun ajouterCommande(@Body credentials: AjouterCommande):  CommandeDto

    @POST("ajouterachat")
    suspend fun ajouterachat(@Body credentials: AjouterAchat):  AchatDto

}