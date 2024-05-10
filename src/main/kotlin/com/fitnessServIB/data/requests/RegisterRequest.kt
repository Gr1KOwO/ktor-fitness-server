package com.fitnessServIB.data.requests

import kotlinx.serialization.Serializable
@Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val fullName: String,
    val age: Int,
    val weight: Float,
    val height: Float,
    val sex: String,
)