package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.UserModel

interface UserRepository {
    suspend fun getUser(email:String):UserModel?
    suspend fun insertUser(user:UserModel)
    suspend fun updateUser(user: UserModel)
    suspend fun deleteUser(email: String)
}