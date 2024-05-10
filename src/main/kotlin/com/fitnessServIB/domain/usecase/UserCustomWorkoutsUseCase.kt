package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.UserCustomWorkoutsModel
import com.fitnessServIB.domain.repository.UserCustomWorkoutsRepository

class UserCustomWorkoutsUseCase (private val repository: UserCustomWorkoutsRepository) {
    suspend fun getUserCustomWorkoutsByUserId(userId: Int): List<UserCustomWorkoutsModel> {
        return repository.getUserCustomWorkoutsByUserId(userId)
    }

    suspend fun getCustomWorkoutsByUserCustomWorkoutsId(customWorkoutsId: Int): List<UserCustomWorkoutsModel> {
        return repository.getCustomWorkoutsByUserCustomWorkoutsId(customWorkoutsId)
    }

    suspend fun addUserCustomWorkout(userCustomWorkoutsModel: UserCustomWorkoutsModel) {
        repository.addUserCustomWorkout(userCustomWorkoutsModel)
    }

    suspend fun removeUserCustomWorkout(userCustomWorkoutsModel: UserCustomWorkoutsModel) {
        repository.removeUserCustomWorkout(userCustomWorkoutsModel)
    }
}