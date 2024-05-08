package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.ExerciseModel

interface ExerciseRepository {
    suspend fun getAllExercises(): List<ExerciseModel>
    suspend fun getExerciseById(id: Int): ExerciseModel?
    suspend fun createExercise(exercise: ExerciseModel)
    suspend fun updateExercise(exercise: ExerciseModel)
    suspend fun deleteExercise(id: Int)
}