package com.fitnessServIB.routes

import com.fitnessServIB.data.model.CustomWorkoutModel
import com.fitnessServIB.data.response.BaseResponse
import com.fitnessServIB.domain.usecase.CustomWorkoutsUseCase
import com.fitnessServIB.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.CustomWorkoutsRoute(customWorkoutsUseCase: CustomWorkoutsUseCase){
    get("fitness-api/v1/CustomWorkouts"){
        try {
            val publicCustomWorkouts = customWorkoutsUseCase.getAllCustomWorkouts().filter { !it.isPrivate }
            call.respond(publicCustomWorkouts)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError,BaseResponse(false,e.message?:Constants.Error.GENERAL))
        }
    }

    get("fitness-api/v1/CustomWorkouts-byId"){
        val customWorkoutsId = call.parameters["id"]?.toIntOrNull()
        if(customWorkoutsId==null){
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@get
        }
        try {
            val customWorkouts = customWorkoutsUseCase.getCustomWorkoutById(customWorkoutsId)
            if (customWorkouts != null) {
                call.respond(HttpStatusCode.OK, customWorkouts)
            } else {
                call.respond(HttpStatusCode.NotFound, BaseResponse(false, Constants.Error.NOT_FOUND))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
        }
    }

    authenticate("jwt") {
        post("fitness-api/v1/CustomWorkouts"){
            val request = call.receiveNullable<CustomWorkoutModel>() ?: run {
                call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.MISSING_FIELDS))
                return@post
            }
            try {
                customWorkoutsUseCase.createCustomWorkout(request)
                call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.CREATED))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
            }
        }
        put("fitness-api/v1/update-customWorkouts"){
            val customWorkoutId = call.parameters["id"]?.toIntOrNull()
            if(customWorkoutId==null){
                call.respond(HttpStatusCode.BadRequest,BaseResponse(false, Constants.Error.INVALID_ID))
                return@put
            }
            val request = call.receiveNullable<CustomWorkoutModel>() ?: run {
                call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.MISSING_FIELDS))
                return@put
            }
            try {
                val existingCustomWorkout = customWorkoutsUseCase.getCustomWorkoutById(customWorkoutId)
                if(existingCustomWorkout!=null){
                    customWorkoutsUseCase.updateCustomWorkout(request.copy(id=customWorkoutId))
                    call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.UPDATED))
                }else{
                    call.respond(HttpStatusCode.NotFound, BaseResponse(false, Constants.Error.NOT_FOUND))
                }
            }catch (e:Exception){
                call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
            }
        }
        delete("fitness-api/v1/del-customWorkouts") {
            val customWorkoutId = call.parameters["id"]?.toIntOrNull()
            if (customWorkoutId == null) {
                call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
                return@delete
            }
            try {
                val existingCustomWorkout = customWorkoutsUseCase.getCustomWorkoutById(customWorkoutId)
                if (existingCustomWorkout != null) {
                    customWorkoutsUseCase.deleteCustomWorkout(customWorkoutId)
                    call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.DELETED))
                } else {
                    call.respond(HttpStatusCode.NotFound, BaseResponse(false, Constants.Error.NOT_FOUND))
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
            }
        }
    }
}