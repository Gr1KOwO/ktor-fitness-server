package com.fitnessServIB.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserExerciseComplexModel(
    val userId: Int,
    val exerciseComplexId: Int
)
