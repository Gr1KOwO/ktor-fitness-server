package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.UserExerciseComplexModel
import com.fitnessServIB.domain.repository.UserExerciseComplexRepository

class UserExerciseComplexUseCase (private val repository: UserExerciseComplexRepository) {
    suspend fun getUserExerciseComplexByUserId(userId: Int): List<UserExerciseComplexModel> =
        repository.getUserExerciseComplexByUserId(userId)

    suspend fun getUserExerciseComplexByExerciseComplexId(exerciseComplexId: Int): List<UserExerciseComplexModel> =
        repository.getUserExerciseComplexByExerciseComplexId(exerciseComplexId)

    suspend fun createUserExerciseComplex(userExerciseComplex: UserExerciseComplexModel) =
        repository.createUserExerciseComplex(userExerciseComplex)

    suspend fun deleteUserExerciseComplex(userId: Int, exerciseComplexId: Int) =
        repository.deleteUserExerciseComplex(userId, exerciseComplexId)
}