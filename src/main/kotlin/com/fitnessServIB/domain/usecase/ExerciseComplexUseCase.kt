package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.ExerciseComplexModel
import com.fitnessServIB.domain.repository.ExerciseComplexRepository

class ExerciseComplexUseCase (private val repository: ExerciseComplexRepository) {
    suspend fun getAllExerciseComplexes(): List<ExerciseComplexModel> = repository.getAllExerciseComplexes()

    suspend fun getExerciseComplexById(id: Int): ExerciseComplexModel? = repository.getExerciseComplexById(id)

    suspend fun createExerciseComplex(exerciseComplex: ExerciseComplexModel) =
        repository.createExerciseComplex(exerciseComplex)

    suspend fun updateExerciseComplex(exerciseComplex: ExerciseComplexModel) =
        repository.updateExerciseComplex(exerciseComplex)

    suspend fun deleteExerciseComplex(id: Int) = repository.deleteExerciseComplex(id)
}