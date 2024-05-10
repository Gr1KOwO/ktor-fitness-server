package com.fitnessServIB.data.requests

import com.fitnessServIB.data.model.UserModel
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val success: Boolean,
    val token: String,
    val user: UserResponse,
)
