package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.ExerciseToTypeModel

interface ExerciseToTypeRepository {
    suspend fun getAllExerciseToTypes(): List<ExerciseToTypeModel>
    suspend fun getExerciseToTypeByExerciseId(exerciseId: Int): List<ExerciseToTypeModel>
    suspend fun getExerciseToTypeByExerciseTypeId(exerciseTypeId: Int): List<ExerciseToTypeModel>
    suspend fun createExerciseToType(exerciseToType: ExerciseToTypeModel)
    suspend fun deleteExerciseToType(exerciseId: Int, exerciseTypeId: Int)
}