package com.example.hotelwallet.data.repository

import com.example.hotelwallet.data.mapper.CategoryMapper
import com.example.hotelwallet.data.source.remote.Api
import com.example.hotelwallet.domain.model.Category
import com.example.hotelwallet.domain.repository.CategoryRepository
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val api: Api,
    private val categoryMapper: CategoryMapper,
) : CategoryRepository {

    override suspend fun getCategories(category : String): Flow<Resource<List<Category>>> = flow {
        try {
            emit(Resource.Loading)
            val categoriesResponse = categoryMapper.mapList(
                api.getCategories(category).menu
            )
            emit(Resource.Success(categoriesResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
