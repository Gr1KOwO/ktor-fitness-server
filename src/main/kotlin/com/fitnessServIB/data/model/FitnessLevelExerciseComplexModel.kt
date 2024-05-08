package com.fitnessServIB.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FitnessLevelExerciseComplexModel(
    val fitnessLevelId: Int,
    val exerciseComplexId: Int
)
