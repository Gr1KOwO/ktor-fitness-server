package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.UserExerciseComplexModel

interface UserExerciseComplexRepository {
    suspend fun getUserExerciseComplexByUserId(userId: String): List<UserExerciseComplexModel>
    suspend fun getUserExerciseComplexByExerciseComplexId(exerciseComplexId: Int): List<UserExerciseComplexModel>
    suspend fun createUserExerciseComplex(userExerciseComplexModel: UserExerciseComplexModel)
    suspend fun deleteUserExerciseComplex(userId: String, exerciseComplexId: Int)
}