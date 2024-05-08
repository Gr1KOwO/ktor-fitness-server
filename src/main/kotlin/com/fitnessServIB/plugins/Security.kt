package com.fitnessServIB.plugins

import com.fitnessServIB.authentification.JwtService
import com.fitnessServIB.data.repository.UserRepositoryImpl
import com.fitnessServIB.domain.usecase.UserUseCase
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {
    val jwtServices = JwtService()
    val repository = UserRepositoryImpl()
    val userUseCase = UserUseCase(repository,jwtServices)

    authentication {
        jwt ("jwt"){
            verifier(jwtServices.getVerifier())
            realm = "Service Server"
            validate {
                val payload = it.payload
                val email = payload.getClaim("email").asString()
                val user = userUseCase.getUserByEmail(email = email)
                user
            }
        }
    }
}
