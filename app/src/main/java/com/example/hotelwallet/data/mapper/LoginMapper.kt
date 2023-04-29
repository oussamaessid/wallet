package com.example.hotelwallet.data.mapper

import com.example.hotelwallet.data.model.UserDto
import com.example.hotelwallet.domain.model.User
import javax.inject.Inject

class LoginMapper @Inject constructor() :
    BaseMapper<UserDto, User> {
    override fun map(from: UserDto): User {
        return User(
            id = from.id,
            name = from.name,
            email = from.email,
            solde = from.solde,
            image = from.image
        )
    }

    override fun mapInverse(from: User): UserDto {
        return UserDto(
            id = from.id,
            name = from.name,
            email = from.email,
            solde = from.solde,
            image = from.image
        )
    }
}