package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.FoodModel

interface FoodRepository {
    suspend fun getAllFoods(): List<FoodModel>
    suspend fun getFoodById(id: Int): FoodModel?
    suspend fun getFoodsByTypeId(typeId: Int): List<FoodModel>
    suspend fun createFood(food: FoodModel)
    suspend fun updateFood(food: FoodModel)
    suspend fun deleteFood(id: Int)
}