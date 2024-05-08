package com.fitnessServIB.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutPerDayModel(
    val id: Int,
    val date: String,
    val exerciseComplex: Int?, // Ссылка на принадлежность дня тренировок к комплексу упражнений
    val customWorkout: Int?,// Ссылка на принадлежность дня тренировок к кастомным пользовательским упражнений
    val isCompleted: Boolean,
    val isSkipped: Boolean,
)
