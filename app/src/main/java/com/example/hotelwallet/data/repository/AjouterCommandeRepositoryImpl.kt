package com.example.hotelwallet.data.repository

import android.util.Log
import com.example.hotelwallet.data.mapper.CommandeMapper
import com.example.hotelwallet.data.source.remote.Api
import com.example.hotelwallet.domain.model.AjouterCommande
import com.example.hotelwallet.domain.model.Commande
import com.example.hotelwallet.domain.repository.AjouterCommandeRepository
import com.example.hotelwallet.utility.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AjouterCommandeRepositoryImpl @Inject constructor(
    private val api: Api,
    private val commandeMapper: CommandeMapper,
) : AjouterCommandeRepository {

    override suspend fun ajouterCommande(
        id_client: Int,
        prix_total: Int
    ): Flow<Resource<Commande>> = flow {
        val commandeRequest = AjouterCommande(id_client, prix_total)
        try {
            emit(Resource.Loading)
            val categoriesResponse = commandeMapper.map(
                api.ajouterCommande(commandeRequest)
            )
            emit(Resource.Success(categoriesResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}
