package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.FitnessLevelExerciseComplexModel

interface FitnessLevelExerciseComplexRepository {
    suspend fun getExerciseComplexesByFitnessLevelId(fitnessLevelId: Int): List<FitnessLevelExerciseComplexModel>
    suspend fun getFitnessLevelsByExerciseComplexId(exerciseComplexId: Int): List<FitnessLevelExerciseComplexModel>
    suspend fun createFitnessLevelExerciseComplex(fitnessLevelExerciseComplex: FitnessLevelExerciseComplexModel)
    suspend fun deleteFitnessLevelExerciseComplex(fitnessLevelId: Int, exerciseComplexId: Int)
}