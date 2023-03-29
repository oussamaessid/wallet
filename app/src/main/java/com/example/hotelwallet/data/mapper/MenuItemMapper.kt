package com.example.hotelwallet.data.mapper

import com.example.hotelwallet.data.model.MenuItemDto
import com.example.hotelwallet.domain.model.MenuItem
import javax.inject.Inject

class MenuItemMapper @Inject constructor() :
    BaseMapper<MenuItemDto, MenuItem> {
    override fun map(from: MenuItemDto): MenuItem {
        return MenuItem(
            idMeal = from.idMeal,
            strMeal = from.strMeal,
            strMealThumb = from.strMealThumb
        )
    }

    override fun mapInverse(from: MenuItem): MenuItemDto {
        return MenuItemDto(
            idMeal = from.idMeal,
            strMeal = from.strMeal,
            strMealThumb = from.strMealThumb
        )
    }
}