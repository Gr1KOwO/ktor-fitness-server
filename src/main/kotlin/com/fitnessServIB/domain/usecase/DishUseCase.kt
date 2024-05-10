package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.DishModel
import com.fitnessServIB.domain.repository.DishRepository

class DishUseCase(private val repository: DishRepository) {
    suspend fun getAllDishes(): List<DishModel> = repository.getAllDishes()
    suspend fun getDishById(id: Int): DishModel? = repository.getDishById(id)
    suspend fun createDish(dish: DishModel) = repository.createDish(dish)
    suspend fun updateDish(dish: DishModel) = repository.updateDish(dish)
    suspend fun deleteDish(id: Int) = repository.deleteDish(id)
}