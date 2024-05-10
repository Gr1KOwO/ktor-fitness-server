package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.ExerciseTypeModel

interface ExerciseTypeRepository {
    suspend fun getExerciseType(id: Int): ExerciseTypeModel?
    suspend fun getAllExerciseTypes(): List<ExerciseTypeModel>
    suspend fun insertExerciseType(exerciseType: ExerciseTypeModel)
    suspend fun updateExerciseType(exerciseType: ExerciseTypeModel)
    suspend fun deleteExerciseType(id: Int)
}