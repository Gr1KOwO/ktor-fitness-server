package com.fitnessServIB.data.model

import kotlinx.serialization.Serializable
@Serializable
data class ExerciseToTypeModel(
    val exerciseId:Int,
    val exerciseTypeId:Int
)
