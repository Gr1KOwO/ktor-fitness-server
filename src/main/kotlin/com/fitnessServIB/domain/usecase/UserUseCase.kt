package com.fitnessServIB.domain.usecase

import com.auth0.jwt.JWTVerifier
import com.fitnessServIB.authentification.JwtService
import com.fitnessServIB.data.model.UserModel
import com.fitnessServIB.domain.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCrypt

class UserUseCase (
    private val repository: UserRepository,
    private val jwtService: JwtService
) {
    suspend fun getUserById(userId:Int) = repository.getUserById(userId = userId)
    suspend fun createdUser(userModel: UserModel) = repository.insertUser(user = userModel)
    suspend fun getUserByEmail(email:String) = repository.getUserByEmail(email)
    suspend fun updateUser(userModel:UserModel) = repository.updateUser(userModel)
    suspend fun deleteUser(userId: Int) = repository.deleteUser(userId)

    fun generateToken(userModel: UserModel):String = jwtService.generateToken(user = userModel)
    fun getGwtVerifier():JWTVerifier = jwtService.getVerifier()
}