package com.fitnessServIB.routes

import com.fitnessServIB.data.model.FitnessLevelModel
import com.fitnessServIB.data.response.BaseResponse
import com.fitnessServIB.domain.usecase.FitnessLevelUseCase
import com.fitnessServIB.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.FitnessLevelRoute(fitnessLevelUseCase: FitnessLevelUseCase){
    get ("fitness-api/v1/fitness-level"){
        try{
            val allFitnessLevels = fitnessLevelUseCase.getAllFitnessLevels()
            call.respond(HttpStatusCode.OK, allFitnessLevels)
        }catch (e:Exception){
            call.respond(HttpStatusCode.BadRequest,BaseResponse(false,e.message?:Constants.Error.GENERAL))
        }
    }
    get("fitness-api/v1/fitness-level-id") {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@get
        }
        try{
            val fitnessLevel = fitnessLevelUseCase.getFitnessLevelById(id)
            if (fitnessLevel == null) {
                call.respond(HttpStatusCode.NotFound, BaseResponse(false, Constants.Error.NOT_FOUND))
            } else {
                call.respond(HttpStatusCode.OK, fitnessLevel)
            }
        }catch (e:Exception){
            call.respond(HttpStatusCode.BadRequest,BaseResponse(false,e.message?:Constants.Error.GENERAL))
        }
    }
    post("fitness-api/v1/fitness-level") {
        val fitnessLevel = call.receiveOrNull<FitnessLevelModel>()
        if (fitnessLevel == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.MISSING_FIELDS))
            return@post
        }
        try{
            fitnessLevelUseCase.createFitnessLevel(fitnessLevel)
            call.respond(HttpStatusCode.Created, BaseResponse(true, Constants.Success.CREATED))
        }catch (e:Exception){
            call.respond(HttpStatusCode.BadRequest,BaseResponse(false,e.message?:Constants.Error.GENERAL))
        }
    }
    put("fitness-api/v1/update-fitness-level") {
        val id = call.parameters["id"]?.toIntOrNull()
        val updatedFitnessLevel = call.receiveOrNull<FitnessLevelModel>()
        if (id == null || updatedFitnessLevel == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.MISSING_FIELDS))
            return@put
        }
        if (id != updatedFitnessLevel.id) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@put
        }
        try {
            fitnessLevelUseCase.updateFitnessLevel(updatedFitnessLevel)
            call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.UPDATED))
        }catch (e:Exception){
            call.respond(HttpStatusCode.BadRequest,BaseResponse(false,e.message?:Constants.Error.GENERAL))
        }
    }
    delete("fitness-api/v1/del-fitness-level") {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@delete
        }
        try {
            fitnessLevelUseCase.deleteFitnessLevel(id)
            call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.DELETED))
        }catch (e:Exception){
            call.respond(HttpStatusCode.BadRequest,BaseResponse(false,e.message?:Constants.Error.GENERAL))
        }
    }

}