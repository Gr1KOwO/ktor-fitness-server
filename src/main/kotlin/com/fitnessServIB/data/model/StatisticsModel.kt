package com.fitnessServIB.data.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class StatisticsModel(
    val idStatistic:Int,
    @Contextual val date: LocalDate,
    val userId: Int,
    val caloriesSpent: Float,
    val caloriesConsumed: Float,
)
