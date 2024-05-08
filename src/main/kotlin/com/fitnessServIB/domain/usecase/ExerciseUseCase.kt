package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.ExerciseModel
import com.fitnessServIB.domain.repository.ExerciseRepository

class ExerciseUseCase (private val exerciseRepository: ExerciseRepository) {

    suspend fun getAllExercises(): List<ExerciseModel> {
        return exerciseRepository.getAllExercises()
    }

    suspend fun getExerciseById(id: Int): ExerciseModel? {
        return exerciseRepository.getExerciseById(id)
    }

    suspend fun createExercise(exercise: ExerciseModel) {
        exerciseRepository.createExercise(exercise)
    }

    suspend fun updateExercise(exercise: ExerciseModel) {
        exerciseRepository.updateExercise(exercise)
    }

    suspend fun deleteExercise(id: Int) {
        exerciseRepository.deleteExercise(id)
    }
}