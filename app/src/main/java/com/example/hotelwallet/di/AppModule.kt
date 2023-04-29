package com.example.hotelwallet.di


import android.content.Context
import androidx.room.Room
import com.example.hotelwallet.data.mapper.*
import com.example.hotelwallet.data.repository.*
import com.example.hotelwallet.data.source.local.AppBasket
import com.example.hotelwallet.data.source.local.BasketsDao
import com.example.hotelwallet.data.source.local.FavoritesDao
import com.example.hotelwallet.data.source.local.datastore.AppDataStoreManager
import com.example.hotelwallet.data.source.remote.Api
import com.example.hotelwallet.data.source.remote.HotelApi
import com.example.hotelwallet.domain.model.Gym
import com.example.hotelwallet.domain.repository.*
import com.example.hotelwallet.utility.BASE_URL
import com.example.hotelwallet.utility.BASE_URL1
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    fun provideBasketsDao(appDatabase: AppBasket): BasketsDao {
        return appDatabase.favoriteDao()
    }

    @Provides
    fun provideFavoritesDao(appDatabase: AppBasket): FavoritesDao {
        return appDatabase.favoritesDao()
    }
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppBasket {
        return Room.databaseBuilder(
            context,
            AppBasket::class.java,
            "Hotel_Wallet_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext appContext: Context): AppDataStore =
        AppDataStoreManager(appContext)

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

    @Singleton
    @Provides
    fun provideLoginRepository(
        api: Api,
        loginMapper: LoginMapper,
        messageMapper: MessageMapper
    ): LoginRepository =
        LoginRepositoryImpl(api = api, loginMapper = loginMapper, messageMapper = messageMapper)

}