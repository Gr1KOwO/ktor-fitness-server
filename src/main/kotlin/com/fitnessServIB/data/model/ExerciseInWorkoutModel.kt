package com.fitnessServIB.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseInWorkoutModel(
    val id: Int,
    val exerciseId: Int, // Ссылка на упражнение
    val workoutPerDayId:Int,//Ссылка на день тренировки
    val sets: Int,
    val reps: Int,
    val weight: Int?,
    val met:Float, // МЕТ для расчёта потраченных калорий за упражнение
    val isCompleted: Boolean,
    val isSkipped: Boolean
)
