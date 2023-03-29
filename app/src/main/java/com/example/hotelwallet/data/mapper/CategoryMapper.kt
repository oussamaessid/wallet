package com.example.hotelwallet.data.mapper

import com.example.hotelwallet.data.model.CategoryDto
import com.example.hotelwallet.domain.model.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor() :
    BaseMapper<CategoryDto, Category> {
    override fun map(from: CategoryDto): Category {
        return Category(
            idCategory = from.idCategory,
            strCategory = from.strCategory,
            strCategoryDescription = from.strCategoryDescription,
            strCategoryThumb = from.strCategoryThumb
        )
    }

    override fun mapInverse(from: Category): CategoryDto {
        return CategoryDto(
            idCategory = from.idCategory,
            strCategory = from.strCategory,
            strCategoryDescription = from.strCategoryDescription,
            strCategoryThumb = from.strCategoryThumb
        )
    }
}