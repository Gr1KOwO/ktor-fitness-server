package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.ExerciseInWorkoutModel

interface ExerciseInWorkoutRepository {
    suspend fun getExercisesByWorkoutPerDayId(workoutPerDayId: Int): List<ExerciseInWorkoutModel>
    suspend fun getAllExercisesInWorkout(): List<ExerciseInWorkoutModel>
    suspend fun createExerciseInWorkout(exerciseInWorkout: ExerciseInWorkoutModel)
    suspend fun updateExerciseInWorkout(exerciseInWorkout: ExerciseInWorkoutModel)
    suspend fun deleteExerciseInWorkout(id: Int)
}