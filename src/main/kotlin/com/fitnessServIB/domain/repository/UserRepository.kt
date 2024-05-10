package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.UserModel

interface UserRepository {
    suspend fun getUserById(userId:Int):UserModel?
    suspend fun getUserByEmail(email:String):UserModel?
    suspend fun insertUser(user:UserModel)
    suspend fun updateUser(user: UserModel)
    suspend fun deleteUser(userId: Int)
}