package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.CustomWorkoutModel

interface CustomWorkoutsRepository {
    suspend fun getAllCustomWorkouts(): List<CustomWorkoutModel>
    suspend fun getCustomWorkoutById(id: Int): CustomWorkoutModel?
    suspend fun createCustomWorkout(customWorkout: CustomWorkoutModel)
    suspend fun updateCustomWorkout(customWorkout: CustomWorkoutModel)
    suspend fun deleteCustomWorkout(id: Int)
}