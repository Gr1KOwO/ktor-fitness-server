package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.FitnessLevelExerciseComplexModel
import com.fitnessServIB.domain.repository.FitnessLevelExerciseComplexRepository

class FitnessLevelExerciseComplexUseCase (private val repository: FitnessLevelExerciseComplexRepository) {
    suspend fun getExerciseComplexesByFitnessLevel(fitnessLevelId: Int): List<FitnessLevelExerciseComplexModel> {
        return repository.getExerciseComplexesByFitnessLevelId(fitnessLevelId)
    }

    suspend fun getFitnessLevelsByExerciseComplex(exerciseComplexId: Int): List<FitnessLevelExerciseComplexModel> {
        return repository.getFitnessLevelsByExerciseComplexId(exerciseComplexId)
    }

    suspend fun createFitnessLevelExerciseComplex(fitnessLevelExerciseComplex: FitnessLevelExerciseComplexModel) =
        repository.createFitnessLevelExerciseComplex(fitnessLevelExerciseComplex)

    suspend fun deleteFitnessLevelExerciseComplex(fitnessLevelId: Int, exerciseComplexId: Int) =
        repository.deleteFitnessLevelExerciseComplex(fitnessLevelId, exerciseComplexId)
}