package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.ConsumedFoodModel
import com.fitnessServIB.domain.repository.ConsumedFoodRepository

class ConsumedFoodUseCase (private val repository: ConsumedFoodRepository) {
    suspend fun getAllConsumedFood(email:String): List<ConsumedFoodModel> = repository.getAllConsumedFood(email)
    suspend fun getConsumedFoodById(id: Int): ConsumedFoodModel? = repository.getConsumedFoodById(id)
    suspend fun createConsumedFood(consumedFood: ConsumedFoodModel) = repository.createConsumedFood(consumedFood)
    suspend fun updateConsumedFood(consumedFood: ConsumedFoodModel) = repository.updateConsumedFood(consumedFood)
    suspend fun deleteConsumedFood(id: Int) = repository.deleteConsumedFood(id)
}