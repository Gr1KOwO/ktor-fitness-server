package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.FoodModel
import com.fitnessServIB.domain.repository.FoodRepository

class FoodUseCase(private val repository: FoodRepository) {
    suspend fun getAllFoods(): List<FoodModel> = repository.getAllFoods()
    suspend fun getFoodById(id: Int): FoodModel? = repository.getFoodById(id)
    suspend fun getFoodsByTypeId(typeId: Int): List<FoodModel> = repository.getFoodsByTypeId(typeId)
    suspend fun createFood(food: FoodModel) = repository.createFood(food)
    suspend fun updateFood(food: FoodModel) = repository.updateFood(food)
    suspend fun deleteFood(id: Int) = repository.deleteFood(id)
}