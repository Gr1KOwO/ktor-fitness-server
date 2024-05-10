package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.CustomWorkoutModel
import com.fitnessServIB.domain.repository.CustomWorkoutsRepository

class CustomWorkoutsUseCase (private val repository: CustomWorkoutsRepository) {
    suspend fun getAllCustomWorkouts(): List<CustomWorkoutModel> {
        return repository.getAllCustomWorkouts()
    }

    suspend fun getCustomWorkoutById(id: Int): CustomWorkoutModel? {
        return repository.getCustomWorkoutById(id)
    }

    suspend fun createCustomWorkout(customWorkout: CustomWorkoutModel) {
        repository.createCustomWorkout(customWorkout)
    }

    suspend fun updateCustomWorkout(customWorkout: CustomWorkoutModel) {
        repository.updateCustomWorkout(customWorkout)
    }

    suspend fun deleteCustomWorkout(id: Int) {
        repository.deleteCustomWorkout(id)
    }
}
