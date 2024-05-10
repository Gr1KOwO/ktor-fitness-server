package com.fitnessServIB.data.requests

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequest(
    val fullName: String? = null,
    val password:String? = null,
    val age: Int? = null,
    val weight: Float? = null,
    val height: Float? = null,
    val sex: String? = null
)
