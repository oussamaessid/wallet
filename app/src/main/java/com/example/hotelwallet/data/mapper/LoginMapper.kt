package com.example.hotelwallet.data.mapper

import com.example.hotelwallet.data.model.UserDto
import com.example.hotelwallet.domain.model.User
import javax.inject.Inject

class LoginMapper @Inject constructor() :
    BaseMapper<UserDto, User> {
    override fun map(from: UserDto): User {
        return User(
            id = from.id,
            nom = from.nom,
            prenom = from.prenom,
            email = from.email,
            solde = from.solde,
            photo = from.photo,
            id_hotel = from.id_hotel,
            statut = from.statut
            )
    }

    override fun mapInverse(from: User): UserDto {
        return UserDto(
            id = from.id,
            nom = from.nom,
            prenom = from.prenom,
            email = from.email,
            solde = from.solde,
            photo = from.photo,
            id_hotel = from.id_hotel,
            statut = from.statut
        )
    }
}