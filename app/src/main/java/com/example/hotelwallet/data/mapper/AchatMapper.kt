package com.example.hotelwallet.data.mapper

import com.example.hotelwallet.data.model.AchatDto
import com.example.hotelwallet.domain.model.Achat
import javax.inject.Inject

class AchatMapper @Inject constructor() :
    BaseMapper<AchatDto, Achat> {
    override fun map(from: AchatDto): Achat {
        return Achat(
            status = from.status,
            success = from.success,
            message = from.message
        )
    }

    override fun mapInverse(from: Achat): AchatDto {
        return AchatDto(
            status = from.status,
            success = from.success,
            message = from.message
        )
    }
}