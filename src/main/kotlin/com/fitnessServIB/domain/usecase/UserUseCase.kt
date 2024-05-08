package com.fitnessServIB.domain.usecase

import com.fitnessServIB.authentification.JwtService
import com.fitnessServIB.data.model.UserModel
import com.fitnessServIB.domain.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCrypt

class UserUseCase (
    private val repository: UserRepository,
    private val jwtService: JwtService
) {
    suspend fun createdUser(userModel: UserModel) = repository.insertUser(user = userModel)
    suspend fun getUserByEmail(email:String) = repository.getUser(email)

    suspend fun authenticateUser(email: String, password: String):String?{
        val user = repository.getUser(email) ?: return null
        return if (BCrypt.checkpw(password, user.password)) {
            jwtService.generateToken(user)
        } else {
            null
        }
    }

    fun generateToken(userModel: UserModel):String = jwtService.generateToken(user = userModel)
}