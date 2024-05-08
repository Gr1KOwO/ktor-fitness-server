package com.fitnessServIB.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FoodTypeModel(
    val id: Int,
    val name: String
)
