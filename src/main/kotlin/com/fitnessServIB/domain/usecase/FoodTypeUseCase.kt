package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.FoodTypeModel
import com.fitnessServIB.domain.repository.FoodTypeRepository

class FoodTypeUseCase(private val repository: FoodTypeRepository) {
    suspend fun getAllFoodTypes(): List<FoodTypeModel> = repository.getAllFoodTypes()
    suspend fun getFoodTypeById(id: Int): FoodTypeModel? = repository.getFoodTypeById(id)
    suspend fun createFoodType(foodType: FoodTypeModel) = repository.createFoodType(foodType)
    suspend fun updateFoodType(foodType: FoodTypeModel) = repository.updateFoodType(foodType)
    suspend fun deleteFoodType(id: Int) = repository.deleteFoodType(id)
}