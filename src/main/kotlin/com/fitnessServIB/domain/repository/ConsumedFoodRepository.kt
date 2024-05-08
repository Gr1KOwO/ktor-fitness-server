package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.ConsumedFoodModel

interface ConsumedFoodRepository {
    suspend fun getAllConsumedFood(userEmail: String): List<ConsumedFoodModel>
    suspend fun getConsumedFoodById(id: Int): ConsumedFoodModel?
    suspend fun createConsumedFood(consumedFood: ConsumedFoodModel)
    suspend fun updateConsumedFood(consumedFood: ConsumedFoodModel)
    suspend fun deleteConsumedFood(id: Int)
}