package com.fitnessServIB

import com.fitnessServIB.plugins.DatabaseFactory.initializationDataBase
import com.fitnessServIB.plugins.configureSecurity
import com.fitnessServIB.plugins.configureSerialization
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    initializationDataBase()
//    configureHTTP()
//    configureSockets()
//    configureMonitoring()
    configureSecurity()
//    configureRouting()
}
