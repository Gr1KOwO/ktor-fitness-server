package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.FitnessLevelModel

interface FitnessLevelRepository {
    suspend fun getAllFitnessLevels(): List<FitnessLevelModel>
    suspend fun getFitnessLevelById(id: Int): FitnessLevelModel?
    suspend fun createFitnessLevel(fitnessLevel: FitnessLevelModel)
    suspend fun updateFitnessLevel(fitnessLevel: FitnessLevelModel)
    suspend fun deleteFitnessLevel(id: Int)
}