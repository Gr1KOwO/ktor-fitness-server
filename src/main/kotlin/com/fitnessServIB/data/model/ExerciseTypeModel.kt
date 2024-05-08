package com.fitnessServIB.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseTypeModel(
    val id: Int,
    val name: String,
)
