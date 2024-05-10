package com.fitnessServIB.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DishModel(
    val id: Int,
    val name: String,
    val description: String,
    val recipe: String,
    val videoLink: String,
    val calories: Float
)
