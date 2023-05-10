package com.example.hotelwallet.di


import android.content.Context
import androidx.room.Room
import com.example.hotelwallet.data.mapper.*
import com.example.hotelwallet.data.repository.*
import com.example.hotelwallet.data.source.local.AppBasket
import com.example.hotelwallet.data.source.local.AppDataStoreManager
import com.example.hotelwallet.data.source.local.BasketsDao
import com.example.hotelwallet.data.source.local.FavoritesDao
import com.example.hotelwallet.data.source.remote.Api
import com.example.hotelwallet.data.source.remote.HotelApi
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
    fun provideMenuRepository(
        api: Api,
        menuMapper: MenuMapper
    ): MenuRepository =
        MenuRepositoryImpl(api = api, menuMapper = menuMapper)

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
        userMapper: UserMapper,
        messageMapper: MessageMapper,
        appDataStore: AppDataStore
    ): AuthenticationRepository =
        AuthenticationRepositoryImpl(
            api = api,
            userMapper = userMapper,
            messageMapper = messageMapper,
            dataStore = appDataStore
        )

    @Singleton
    @Provides
    fun provideProfileRepository(
        api: Api,
        userMapper: UserMapper,
        appDataStore: AppDataStore
    ): ProfileRepository =
        ProfileRepositoryImpl(api = api, userMapper = userMapper, dataStore = appDataStore)

    @Singleton
    @Provides
    fun provideLanguageRepository(
        appDataStore: AppDataStore
    ): LanguageRepository =
        LanguageRepositoryImpl(dataStore = appDataStore)
}