package com.fitnessServIB.data.model

import kotlinx.serialization.Serializable
@Serializable
data class UserCustomWorkoutsModel(
    val userId:String,
    val customWorkoutsId:Int
)
