package com.fitnessServIB.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CustomWorkoutModel(
    val id: Int,
    val name: String,
    val description: String,
    val isPrivate: Boolean,
)
