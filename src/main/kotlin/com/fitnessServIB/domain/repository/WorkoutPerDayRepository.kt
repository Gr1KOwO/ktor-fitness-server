package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.WorkoutPerDayModel

interface WorkoutPerDayRepository {
    suspend fun getWorkoutPerDayById(id: Int): WorkoutPerDayModel?
    suspend fun getAllWorkoutsPerDay(): List<WorkoutPerDayModel>
    suspend fun createWorkoutPerDay(workoutPerDay: WorkoutPerDayModel)
    suspend fun updateWorkoutPerDay(workoutPerDay: WorkoutPerDayModel)
    suspend fun deleteWorkoutPerDay(id: Int)
}