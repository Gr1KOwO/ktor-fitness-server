package com.fitnessServIB.data.model

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val email: String,
    val password: String,
    val fullName: String,
    val age: Int,
    val weight: Float,
    val height: Float,
    val sex: String,
):Principal
