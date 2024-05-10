package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.FoodTypeModel

interface FoodTypeRepository {
    suspend fun getAllFoodTypes(): List<FoodTypeModel>
    suspend fun getFoodTypeById(id: Int): FoodTypeModel?
    suspend fun createFoodType(foodType: FoodTypeModel)
    suspend fun updateFoodType(foodType: FoodTypeModel)
    suspend fun deleteFoodType(id: Int)
}