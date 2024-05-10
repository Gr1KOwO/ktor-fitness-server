package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.FitnessLevelModel
import com.fitnessServIB.domain.repository.FitnessLevelRepository

class FitnessLevelUseCase (private val repository: FitnessLevelRepository) {
    suspend fun getAllFitnessLevels(): List<FitnessLevelModel> = repository.getAllFitnessLevels()

    suspend fun getFitnessLevelById(id: Int): FitnessLevelModel? = repository.getFitnessLevelById(id)

    suspend fun createFitnessLevel(fitnessLevel: FitnessLevelModel) =
        repository.createFitnessLevel(fitnessLevel)

    suspend fun updateFitnessLevel(fitnessLevel: FitnessLevelModel) =
        repository.updateFitnessLevel(fitnessLevel)

    suspend fun deleteFitnessLevel(id: Int) = repository.deleteFitnessLevel(id)
}