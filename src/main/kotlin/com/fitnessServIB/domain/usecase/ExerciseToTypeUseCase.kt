package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.ExerciseToTypeModel
import com.fitnessServIB.domain.repository.ExerciseToTypeRepository

class ExerciseToTypeUseCase (private val repository: ExerciseToTypeRepository) {
    suspend fun getAllExerciseToTypes(): List<ExerciseToTypeModel> = repository.getAllExerciseToTypes()

    suspend fun getExerciseTypeByExerciseId(exerciseId: Int): List<ExerciseToTypeModel> =
        repository.getExerciseToTypeByExerciseId(exerciseId)

    suspend fun getExerciseByExerciseTypeId(exerciseTypeId: Int): List<ExerciseToTypeModel> =
        repository.getExerciseToTypeByExerciseTypeId(exerciseTypeId)

    suspend fun createExerciseToType(exerciseToType: ExerciseToTypeModel) =
        repository.createExerciseToType(exerciseToType)

    suspend fun deleteExerciseToType(exerciseId: Int, exerciseTypeId: Int) =
        repository.deleteExerciseToType(exerciseId, exerciseTypeId)
}