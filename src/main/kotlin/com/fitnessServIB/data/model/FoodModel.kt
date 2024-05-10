package com.fitnessServIB.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FoodModel(
    val id: Int,
    val name: String,
    val foodTypeId: Int,
    val calories: Float
)
