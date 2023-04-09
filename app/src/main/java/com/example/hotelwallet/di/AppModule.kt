package com.example.hotelwallet.di


import com.example.hotelwallet.data.mapper.CategoryMapper
import com.example.hotelwallet.data.mapper.GymMapper
import com.example.hotelwallet.data.mapper.MenuItemMapper
import com.example.hotelwallet.data.mapper.ServiceMapper
import com.example.hotelwallet.data.repository.CategoryRepositoryImpl
import com.example.hotelwallet.data.repository.GymRepositoryImpl
import com.example.hotelwallet.data.repository.MenuItemRepositoryImpl
import com.example.hotelwallet.data.repository.ServicesRepositoryImpl
import com.example.hotelwallet.data.source.remote.Api
import com.example.hotelwallet.data.source.remote.HotelApi
import com.example.hotelwallet.domain.model.Gym
import com.example.hotelwallet.domain.repository.CategoryRepository
import com.example.hotelwallet.domain.repository.GymRepository
import com.example.hotelwallet.domain.repository.MenuRepository
import com.example.hotelwallet.domain.repository.ServiceRepository
import com.example.hotelwallet.utility.BASE_URL
import com.example.hotelwallet.utility.BASE_URL1
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHoteloApi(): HotelApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(HotelApi::class.java)
    }

    @Singleton
    @Provides
    fun provideApi(): Api {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL1)
            .build()
            .create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideCategoryRepository(
        api: Api,
        categoryMapper: CategoryMapper
    ): CategoryRepository =
        CategoryRepositoryImpl(api = api, categoryMapper = categoryMapper)

    @Singleton
    @Provides
    fun provideMenuItemRepository(
        api: Api,
        menuItemMapper: MenuItemMapper
    ): MenuRepository =
        MenuItemRepositoryImpl(api = api, menuItemMapper = menuItemMapper)

    @Singleton
    @Provides
    fun provideServiceRepository(
        api: Api,
        serviceMapper: ServiceMapper,
    ): ServiceRepository =
        ServicesRepositoryImpl(api = api, serviceMapper = serviceMapper)

    @Singleton
    @Provides
    fun provideGymRepository(
        api: Api,
        gymMapper: GymMapper
    ): GymRepository =
        GymRepositoryImpl(api = api, gymMapper = gymMapper)
}