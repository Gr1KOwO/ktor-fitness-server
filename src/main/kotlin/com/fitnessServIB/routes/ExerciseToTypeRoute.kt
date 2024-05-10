package com.fitnessServIB.routes

import com.fitnessServIB.data.model.ExerciseToTypeModel
import com.fitnessServIB.data.response.BaseResponse
import com.fitnessServIB.domain.usecase.ExerciseToTypeUseCase
import com.fitnessServIB.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.ExerciseToTypeModel(exerciseToTypeUseCase: ExerciseToTypeUseCase){
    get("fitness-api/v1/exercise-to-type") {
        val allExerciseToTypes = exerciseToTypeUseCase.getAllExerciseToTypes()
        call.respond(allExerciseToTypes)
    }
    get("fitness-api/v1/exercise-type-by-exerciseId") {
        val exerciseId = call.parameters["id"]?.toIntOrNull()
        if (exerciseId == null) {
            call.respond(HttpStatusCode.BadRequest,BaseResponse(false, Constants.Error.INVALID_ID))
            return@get
        }
        val exerciseToTypes = exerciseToTypeUseCase.getExerciseTypeByExerciseId(exerciseId)
        call.respond(exerciseToTypes)
    }
    get("fitness-api/v1/exercise-by-exercise-typeId") {
        val exerciseTypeId = call.parameters["id"]?.toIntOrNull()
        if (exerciseTypeId == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false,Constants.Error.INVALID_ID))
            return@get
        }
        val exerciseToTypes = exerciseToTypeUseCase.getExerciseByExerciseTypeId(exerciseTypeId)
        call.respond(HttpStatusCode.OK,exerciseToTypes)
    }
    post("fitness-api/v1/exercise-to-type") {
        val exerciseToType = call.receiveOrNull<ExerciseToTypeModel>()
        if (exerciseToType == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false,Constants.Error.MISSING_FIELDS))
            return@post
        }
        exerciseToTypeUseCase.createExerciseToType(exerciseToType)
        call.respond(HttpStatusCode.OK,BaseResponse(true,Constants.Success.CREATED))
    }
    delete("fitness-api/v1/del-exercise-to-type") {
        val exerciseId = call.parameters["exerciseId"]?.toIntOrNull()
        val exerciseTypeId = call.parameters["exerciseTypeId"]?.toIntOrNull()
        if (exerciseId == null || exerciseTypeId == null) {
            call.respond(HttpStatusCode.BadRequest, Constants.Error.INVALID_ID)
            return@delete
        }
        exerciseToTypeUseCase.deleteExerciseToType(exerciseId, exerciseTypeId)
        call.respond(HttpStatusCode.OK,BaseResponse(true,Constants.Success.DELETED))
    }
}