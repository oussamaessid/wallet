package com.example.hotelwallet.data.mapper

import com.example.hotelwallet.data.model.CommandeDto
import com.example.hotelwallet.data.model.MessageDto
import com.example.hotelwallet.domain.model.Commande
import com.example.hotelwallet.domain.model.Message
import javax.inject.Inject

class CommandeMapper @Inject constructor() :
    BaseMapper<CommandeDto, Commande> {
    override fun map(from: CommandeDto): Commande {
        return Commande(
            status = from.status,
            success = from.success,
            message = from.message
        )
    }

    override fun mapInverse(from: Commande): CommandeDto {
        return CommandeDto(
            status = from.status,
            success = from.success,
            message = from.message
        )
    }
}