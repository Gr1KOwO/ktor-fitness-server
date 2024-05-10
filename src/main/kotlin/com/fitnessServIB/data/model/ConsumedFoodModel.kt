package com.fitnessServIB.data.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class ConsumedFoodModel(
    val id: Int,
    @Contextual val date: LocalDate,
    val userId: Int,
    val foodId: Int?,
    val dishId: Int?,
    val quantity: Int,
    val calories: Float
)
