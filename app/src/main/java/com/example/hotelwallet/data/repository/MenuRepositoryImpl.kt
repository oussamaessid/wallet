package com.example.hotelwallet.data.repository

import com.example.hotelwallet.data.mapper.MenuMapper
import com.example.hotelwallet.data.source.remote.Api
import com.example.hotelwallet.domain.model.Menu
import com.example.hotelwallet.domain.repository.MenuRepository
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val api: Api,
    private val menuMapper: MenuMapper,
) : MenuRepository {

    override suspend fun getMenuByServiceId(serviceId: Int): Flow<Resource<List<Menu>>> = flow {
        try {
            emit(Resource.Loading)
            val categoriesResponse = menuMapper.mapList(
                api.getMenuByServiceId(serviceId).menu
            )
            emit(Resource.Success(categoriesResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
