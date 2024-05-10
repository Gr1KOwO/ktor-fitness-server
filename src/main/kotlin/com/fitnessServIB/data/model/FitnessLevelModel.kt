package com.fitnessServIB.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FitnessLevelModel(
    val id: Int,
    val name: String,
)
