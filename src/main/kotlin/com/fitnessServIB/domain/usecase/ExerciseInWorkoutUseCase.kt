package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.ExerciseInWorkoutModel
import com.fitnessServIB.domain.repository.ExerciseInWorkoutRepository

class ExerciseInWorkoutUseCase (private val repository: ExerciseInWorkoutRepository) {

    suspend fun getExercisesByWorkoutPerDayId(workoutPerDayId: Int): List<ExerciseInWorkoutModel> {
        return repository.getExercisesByWorkoutPerDayId(workoutPerDayId)
    }

    suspend fun getAllExercisesInWorkout(): List<ExerciseInWorkoutModel> {
        return repository.getAllExercisesInWorkout()
    }

    suspend fun createExerciseInWorkout(exerciseInWorkout: ExerciseInWorkoutModel) {
        repository.createExerciseInWorkout(exerciseInWorkout)
    }

    suspend fun updateExerciseInWorkout(exerciseInWorkout: ExerciseInWorkoutModel) {
        repository.updateExerciseInWorkout(exerciseInWorkout)
    }

    suspend fun deleteExerciseInWorkout(id: Int) {
        repository.deleteExerciseInWorkout(id)
    }
}