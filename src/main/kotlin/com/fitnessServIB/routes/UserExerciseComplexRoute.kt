package com.fitnessServIB.routes

import com.fitnessServIB.data.model.UserExerciseComplexModel
import com.fitnessServIB.data.response.BaseResponse
import com.fitnessServIB.domain.usecase.UserExerciseComplexUseCase
import com.fitnessServIB.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.UserExerciseComplexRoute(userExerciseComplexUseCase: UserExerciseComplexUseCase){
    get("fitness-api/v1/user-by-ExerciseId"){
        val exerciseComplexId = call.parameters["exerciseComplexId"]?.toIntOrNull()
        if (exerciseComplexId == null) {
            call.respond(HttpStatusCode.BadRequest,BaseResponse(false,Constants.Error.INVALID_ID))
            return@get
        }
        try {
            val userExerciseComplex = userExerciseComplexUseCase.getUserExerciseComplexByExerciseComplexId(exerciseComplexId)
            call.respond(HttpStatusCode.OK,userExerciseComplex)
        }catch (e:Exception){
            call.respond(HttpStatusCode.BadRequest,BaseResponse(false,e.message?:Constants.Error.GENERAL))
        }

    }
    get("fitness-api/v1/Exercise-by-UserId"){
        val userId = call.parameters["userId"]?.toIntOrNull()
        if (userId == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }
        try {
        val userExerciseComplex = userExerciseComplexUseCase.getUserExerciseComplexByUserId(userId)
        call.respond(userExerciseComplex)
        }catch (e:Exception){
            call.respond(HttpStatusCode.BadRequest,BaseResponse(false,e.message?:Constants.Error.GENERAL))
        }
    }
    put("fitness-api/v1/userExerciseComplex"){
        try {
            val userExerciseComplex = call.receive<UserExerciseComplexModel>()
            userExerciseComplexUseCase.createUserExerciseComplex(userExerciseComplex)
            call.respond(HttpStatusCode.OK,BaseResponse(true,Constants.Success.CREATED))
        }catch (e:Exception){
            call.respond(HttpStatusCode.BadRequest,BaseResponse(false,e.message?:Constants.Error.GENERAL))
        }
    }
    delete("fitness-api/v1/userExerciseComplex") {
        try {
            val userExerciseComplex = call.receive<UserExerciseComplexModel>()
            userExerciseComplexUseCase.deleteUserExerciseComplex(userExerciseComplex.userId, userExerciseComplex.exerciseComplexId)
            call.respond(HttpStatusCode.OK,BaseResponse(true,Constants.Success.DELETED))
        }catch (e:Exception){
            call.respond(HttpStatusCode.BadRequest,BaseResponse(false,e.message?:Constants.Error.GENERAL))
        }
    }
}