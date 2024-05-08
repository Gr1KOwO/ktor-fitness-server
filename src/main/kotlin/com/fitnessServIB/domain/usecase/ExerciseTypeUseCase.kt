package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.ExerciseTypeModel
import com.fitnessServIB.domain.repository.ExerciseTypeRepository

class ExerciseTypeUseCase(private val repository: ExerciseTypeRepository) {
    suspend fun getExerciseType(id: Int): ExerciseTypeModel? = repository.getExerciseType(id)

    suspend fun getAllExerciseTypes(): List<ExerciseTypeModel> = repository.getAllExerciseTypes()

    suspend fun createExerciseType(exerciseType: ExerciseTypeModel) {
        repository.insertExerciseType(exerciseType)
    }

    suspend fun updateExerciseType(exerciseType: ExerciseTypeModel) {
        repository.updateExerciseType(exerciseType)
    }

    suspend fun deleteExerciseType(id: Int) {
        repository.deleteExerciseType(id)
    }
}