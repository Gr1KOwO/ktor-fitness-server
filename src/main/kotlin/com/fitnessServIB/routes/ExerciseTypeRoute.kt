package com.fitnessServIB.routes

import com.fitnessServIB.data.model.ExerciseTypeModel
import com.fitnessServIB.data.response.BaseResponse
import com.fitnessServIB.domain.usecase.ExerciseTypeUseCase
import com.fitnessServIB.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.ExerciseTypeRoute(exerciseTypeUseCase: ExerciseTypeUseCase)
{
    get("fitness-api/v1/exercises-types") {
        try {
            val exerciseTypes = exerciseTypeUseCase.getAllExerciseTypes()
            call.respond(HttpStatusCode.OK, exerciseTypes)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
        }
    }

    get("fitness-api/v1/exercise-type") {
        try {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, BaseResponse(false, "Invalid ID parameter"))
            } else {
                val exerciseType = exerciseTypeUseCase.getExerciseType(id)
                if (exerciseType == null) {
                    call.respond(HttpStatusCode.NotFound, BaseResponse(false, "Exercise type not found"))
                } else {
                    call.respond(HttpStatusCode.OK,  exerciseType)
                }
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
        }
    }

    post("fitness-api/v1/create-exercise-type") {
        try {
            val exerciseType = call.receiveOrNull<ExerciseTypeModel>()
            if (exerciseType == null) {
                call.respond(HttpStatusCode.BadRequest, BaseResponse(false, "Invalid exercise type data"))
            } else {
                exerciseTypeUseCase.createExerciseType(exerciseType)
                call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.CREATED))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
        }
    }

    put("fitness-api/v1/update-exercise-types") {
        try {
            val id = call.parameters["id"]?.toIntOrNull()
            val exerciseType = call.receiveOrNull<ExerciseTypeModel>()
            if (id == null || exerciseType == null) {
                call.respond(HttpStatusCode.BadRequest, BaseResponse(false, "Invalid ID parameter or exercise type data"))
            } else {
                exerciseTypeUseCase.updateExerciseType(exerciseType.copy(id = id))
                call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.UPDATED))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
        }
    }

    delete("fitness-api/v1/delete-exercise-types") {
        try {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, BaseResponse(false, "Invalid ID parameter"))
            } else {
                exerciseTypeUseCase.deleteExerciseType(id)
                call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.DELETED))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
        }
    }
}