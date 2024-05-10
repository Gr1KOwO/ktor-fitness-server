package com.fitnessServIB.routes

import com.fitnessServIB.data.model.ExerciseModel
import com.fitnessServIB.data.response.BaseResponse
import com.fitnessServIB.domain.usecase.ExerciseUseCase
import com.fitnessServIB.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.ExerciseRoute(exerciseUseCase: ExerciseUseCase)
{
    get("fitness-api/v1/exercises") {
        try {
            val exercises = exerciseUseCase.getAllExercises()
            call.respond(HttpStatusCode.OK,  exercises)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
        }
    }

    get("fitness-api/v1/exercise") {
        val exerciseId = call.parameters["id"]?.toIntOrNull()
        if (exerciseId == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@get
        }

        try {
            val exercise = exerciseUseCase.getExerciseById(exerciseId)
            if (exercise != null) {
                call.respond(HttpStatusCode.OK,  exercise)
            } else {
                call.respond(HttpStatusCode.NotFound, BaseResponse(false, Constants.Error.USER_NOT_FOUND))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
        }
    }

    post("fitness-api/v1/create-exercise") {
        val newExercise = call.receive<ExerciseModel>()
        try {
            exerciseUseCase.createExercise(newExercise)
            call.respond(HttpStatusCode.Created, BaseResponse(true, Constants.Success.CREATED))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
        }
    }

    put("fitness-api/v1/update-exercise") {
        val exerciseId = call.parameters["id"]?.toIntOrNull()
        if (exerciseId == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@put
        }

        val updatedExercise = call.receive<ExerciseModel>()
        if (updatedExercise.id != exerciseId) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@put
        }

        try {
            exerciseUseCase.updateExercise(updatedExercise)
            call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.UPDATED))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
        }
    }

    delete("fitness-api/v1/del-exercises") {
        val exerciseId = call.parameters["id"]?.toIntOrNull()
        if (exerciseId == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@delete
        }

        try {
            exerciseUseCase.deleteExercise(exerciseId)
            call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.DELETED))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
        }
    }
}