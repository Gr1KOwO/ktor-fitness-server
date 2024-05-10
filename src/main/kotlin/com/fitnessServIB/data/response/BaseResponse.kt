package com.fitnessServIB.data.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
    val success:Boolean,
    val message:String,
)
