package com.fitnessServIB.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseModel(
    val id: Int,
    val name: String,
    val description: String,
    val urlGif:String?,
    val medicalContraindications:String,
)
