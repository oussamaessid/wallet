package com.example.hotelwallet.data.repository

import com.example.hotelwallet.data.mapper.AchatMapper
import com.example.hotelwallet.data.source.remote.Api
import com.example.hotelwallet.domain.model.Achat
import com.example.hotelwallet.domain.model.AjouterAchat
import com.example.hotelwallet.domain.repository.AjouterAchatRepository
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AjouterAchatRepositoryImpl @Inject constructor(
    private val api: Api,
    private val achatMapper: AchatMapper,
) : AjouterAchatRepository {

    override suspend fun ajouterAchat(
        id_client: Int,
        id_plan: Int
    ): Flow<Resource<Achat>> = flow {
        val commandeRequest = AjouterAchat(id_client, id_plan)
        try {
            emit(Resource.Loading)
            val categoriesResponse = achatMapper.map(
                api.ajouterachat(commandeRequest)
            )
            emit(Resource.Success(categoriesResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}
