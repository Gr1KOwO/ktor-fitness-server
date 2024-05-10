package com.fitnessServIB.authentification

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.fitnessServIB.data.model.UserModel
import java.time.LocalDateTime
import java.time.ZoneOffset

class JwtService {
    private val issuer = "FitAppSerIB"
    private val jwtSecret = System.getenv("JWT_SECRET")
    private val algorithm = Algorithm.HMAC512(jwtSecret)

    private val verifier:JWTVerifier = JWT.
    require(algorithm).
    withIssuer(issuer).
    build()

    fun generateToken(user:UserModel):String{
        return JWT.create().
        withSubject("FitAppAuth").
        withIssuer(issuer).
        withClaim("email",user.email).
        withExpiresAt(LocalDateTime.now().plusDays(30).toInstant(ZoneOffset.UTC)).
        sign(algorithm)
    }
    fun getVerifier():JWTVerifier = verifier
}