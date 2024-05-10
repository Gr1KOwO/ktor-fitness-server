package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.UserCustomWorkoutsModel

interface UserCustomWorkoutsRepository {
    suspend fun getUserCustomWorkoutsByUserId(userId: Int): List<UserCustomWorkoutsModel>
    suspend fun getCustomWorkoutsByUserCustomWorkoutsId(customWorkoutsId: Int): List<UserCustomWorkoutsModel>
    suspend fun addUserCustomWorkout(userCustomWorkoutsModel: UserCustomWorkoutsModel)
    suspend fun removeUserCustomWorkout(userCustomWorkoutsModel: UserCustomWorkoutsModel)
}