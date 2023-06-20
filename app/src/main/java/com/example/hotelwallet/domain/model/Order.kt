package com.example.hotelwallet.domain.model

data class Order(
    val id: Int,
    val createdAt: Long,
    val totalPrice: Double,
    val platList: List<SubMenu> = emptyList(),
    val planList: List<Plan> = emptyList()
)
