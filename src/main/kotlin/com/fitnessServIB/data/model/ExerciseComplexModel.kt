package com.fitnessServIB.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseComplexModel(
    val id: Int,
    val name: String,
    val description: String,
)
