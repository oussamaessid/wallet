package com.example.hotelwallet.data.repository

import android.util.Log
import com.example.hotelwallet.data.mapper.GymMapper
import com.example.hotelwallet.data.mapper.MenuItemMapper
import com.example.hotelwallet.data.source.remote.Api
import com.example.hotelwallet.domain.model.Gym
import com.example.hotelwallet.domain.model.MenuItem
import com.example.hotelwallet.domain.repository.GymRepository
import com.example.hotelwallet.domain.repository.MenuRepository
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GymRepositoryImpl @Inject constructor(
    private val api: Api,
    private val gymMapper: GymMapper,
) : GymRepository {
    override suspend fun getGym(category: String): Flow<Resource<List<Gym>>> = flow {
        try {
            emit(Resource.Loading)
            val categoriesResponse = gymMapper.mapList(
                api.getGym(category).plans
            )
            Log.println(Log.ASSERT,"categoriesResponse",categoriesResponse.toString())
            emit(Resource.Success(categoriesResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}
