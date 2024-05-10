package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.WorkoutPerDayModel
import com.fitnessServIB.domain.repository.WorkoutPerDayRepository

class WorkoutPerDayUseCase (private val repository: WorkoutPerDayRepository) {
    suspend fun getWorkoutPerDayById(id: Int): WorkoutPerDayModel? =
        repository.getWorkoutPerDayById(id)

    suspend fun getAllWorkoutsPerDay(): List<WorkoutPerDayModel> =
        repository.getAllWorkoutsPerDay()

    suspend fun createWorkoutPerDay(workoutPerDay: WorkoutPerDayModel) =
        repository.createWorkoutPerDay(workoutPerDay)

    suspend fun updateWorkoutPerDay(workoutPerDay: WorkoutPerDayModel) =
        repository.updateWorkoutPerDay(workoutPerDay)

    suspend fun deleteWorkoutPerDay(id: Int) =
        repository.deleteWorkoutPerDay(id)
}