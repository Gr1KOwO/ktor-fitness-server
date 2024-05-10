package com.fitnessServIB.data.requests

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse (
    val userId:Int,
    val fullName: String,
    val age: Int,
    val weight: Float,
    val height: Float,
    val sex: String,
)