package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.ExerciseComplexModel

interface ExerciseComplexRepository {
    suspend fun getAllExerciseComplexes(): List<ExerciseComplexModel>
    suspend fun getExerciseComplexById(id: Int): ExerciseComplexModel?
    suspend fun createExerciseComplex(exerciseComplex: ExerciseComplexModel)
    suspend fun updateExerciseComplex(exerciseComplex: ExerciseComplexModel)
    suspend fun deleteExerciseComplex(id: Int)
}