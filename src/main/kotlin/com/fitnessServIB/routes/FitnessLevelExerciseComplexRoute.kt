package com.fitnessServIB.routes

import com.fitnessServIB.data.model.FitnessLevelExerciseComplexModel
import com.fitnessServIB.data.response.BaseResponse
import com.fitnessServIB.domain.usecase.FitnessLevelExerciseComplexUseCase
import com.fitnessServIB.utils.Constants
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.FitnessLevelExerciseComplexRoute(fitnessLevelExerciseComplexUseCase: FitnessLevelExerciseComplexUseCase) {
    get("fitness-api/v1/fitness-level-exercise-complex") {
        try {
            val allFitnessLevelExerciseComplex =
                fitnessLevelExerciseComplexUseCase.getAllFitnessLevelExerciseComplex()
            call.respond(allFitnessLevelExerciseComplex)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError,BaseResponse(false,e.message?:Constants.Error.GENERAL))
        }
    }

    get("fitness-api/v1/fitness-exercise-complex-byFitnessLevelId") {
        val fitnessLevelId = call.parameters["fitnessLevelId"]?.toIntOrNull()
        if (fitnessLevelId == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@get
        }
        val exerciseComplexes = fitnessLevelExerciseComplexUseCase.getExerciseComplexesByFitnessLevel(fitnessLevelId)
        call.respond(HttpStatusCode.OK, exerciseComplexes)
    }
    get("fitness-api/v1/fitness-FitnessLevel-complex-byExerciseId") {
        val exerciseComplexId = call.parameters["exerciseComplexId"]?.toIntOrNull()
        if (exerciseComplexId == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@get
        }
        val fitnessLevels = fitnessLevelExerciseComplexUseCase.getFitnessLevelsByExerciseComplex(exerciseComplexId)
        call.respond(HttpStatusCode.OK, fitnessLevels)
    }
    post("fitness-api/v1/fitness-level-exercise-complex") {
        val fitnessLevelExerciseComplex = call.receiveOrNull<FitnessLevelExerciseComplexModel>()
        if (fitnessLevelExerciseComplex == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.MISSING_FIELDS))
            return@post
        }
        fitnessLevelExerciseComplexUseCase.createFitnessLevelExerciseComplex(fitnessLevelExerciseComplex)
        call.respond(HttpStatusCode.Created, BaseResponse(true, Constants.Success.CREATED))
    }
    delete("fitness-api/v1/del-fitness-level-exercise-complex") {
        val fitnessLevelId = call.parameters["fitnessLevelId"]?.toIntOrNull()
        val exerciseComplexId = call.parameters["exerciseComplexId"]?.toIntOrNull()
        if (fitnessLevelId == null || exerciseComplexId == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.MISSING_FIELDS))
            return@delete
        }
        fitnessLevelExerciseComplexUseCase.deleteFitnessLevelExerciseComplex(fitnessLevelId, exerciseComplexId)
        call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.DELETED))
    }
}