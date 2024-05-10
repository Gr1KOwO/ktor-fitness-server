package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.ConsumedFoodModel
import kotlinx.datetime.LocalDate

interface ConsumedFoodRepository {
    suspend fun getAllConsumedFood(userId: Int): List<ConsumedFoodModel>
    suspend fun getConsumedFoodById(id: Int): ConsumedFoodModel?
    suspend fun getConsumedFoodByDate(userId: Int, date: LocalDate): List<ConsumedFoodModel>
    suspend fun createConsumedFood(consumedFood: ConsumedFoodModel)
    suspend fun updateConsumedFood(consumedFood: ConsumedFoodModel)
    suspend fun deleteConsumedFood(id: Int)
}