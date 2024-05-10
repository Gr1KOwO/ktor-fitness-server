package com.fitnessServIB.routes

import com.fitnessServIB.data.model.ExerciseComplexModel
import com.fitnessServIB.data.response.BaseResponse
import com.fitnessServIB.domain.usecase.ExerciseComplexUseCase
import com.fitnessServIB.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.ExerciseComplexRoute(exerciseComplexUseCase: ExerciseComplexUseCase){
    get("fitness-api/v1/exercise-complex") {
        val allExerciseComplexes = exerciseComplexUseCase.getAllExerciseComplexes()
        call.respond(HttpStatusCode.OK, allExerciseComplexes)
    }
    get("fitness-api/v1/exercise-complex-id") {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@get
        }
        val exerciseComplex = exerciseComplexUseCase.getExerciseComplexById(id)
        if (exerciseComplex == null) {
            call.respond(HttpStatusCode.NotFound, BaseResponse(false, Constants.Error.NOT_FOUND))
        } else {
            call.respond(HttpStatusCode.OK, exerciseComplex)
        }
    }
    post("fitness-api/v1/exercise-complex") {
        val exerciseComplex = call.receiveOrNull<ExerciseComplexModel>()
        if (exerciseComplex == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.MISSING_FIELDS))
            return@post
        }
        exerciseComplexUseCase.createExerciseComplex(exerciseComplex)
        call.respond(HttpStatusCode.Created, BaseResponse(true, Constants.Success.CREATED))
    }
    put("fitness-api/v1/update-exercise-complex") {
        val id = call.parameters["id"]?.toIntOrNull()
        val updatedExerciseComplex = call.receiveOrNull<ExerciseComplexModel>()
        if (id == null || updatedExerciseComplex == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.MISSING_FIELDS))
            return@put
        }
        if (id != updatedExerciseComplex.id) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@put
        }
        exerciseComplexUseCase.updateExerciseComplex(updatedExerciseComplex)
        call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.UPDATED))
    }
    delete("fitness-api/v1/del-exercise-complex") {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@delete
        }
        exerciseComplexUseCase.deleteExerciseComplex(id)
        call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.DELETED))
    }
}