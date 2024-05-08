package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.DishModel

interface DishRepository {
    suspend fun getAllDishes(): List<DishModel>
    suspend fun getDishById(id: Int): DishModel?
    suspend fun createDish(dish: DishModel)
    suspend fun updateDish(dish: DishModel)
    suspend fun deleteDish(id: Int)
}