package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.ConsumedFoodModel
import com.fitnessServIB.domain.repository.ConsumedFoodRepository
import kotlinx.datetime.LocalDate

class ConsumedFoodUseCase (private val repository: ConsumedFoodRepository) {
    suspend fun getAllConsumedFood(userId:Int): List<ConsumedFoodModel> = repository.getAllConsumedFood(userId)
    suspend fun getConsumedFoodById(id: Int): ConsumedFoodModel? = repository.getConsumedFoodById(id)
    suspend fun createConsumedFood(consumedFood: ConsumedFoodModel) = repository.createConsumedFood(consumedFood)
    suspend fun updateConsumedFood(consumedFood: ConsumedFoodModel) = repository.updateConsumedFood(consumedFood)
    suspend fun deleteConsumedFood(id: Int) = repository.deleteConsumedFood(id)
    suspend fun getConsumedFoodByDate(userId: Int, date: LocalDate): List<ConsumedFoodModel> = repository.getConsumedFoodByDate(userId, date)
}