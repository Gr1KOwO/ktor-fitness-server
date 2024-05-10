package com.fitnessServIB.routes

import com.fitnessServIB.data.model.ExerciseInWorkoutModel
import com.fitnessServIB.data.response.BaseResponse
import com.fitnessServIB.domain.usecase.ExerciseInWorkoutUseCase
import com.fitnessServIB.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.ExerciseInWorkoutRoute(exerciseInWorkoutUseCase: ExerciseInWorkoutUseCase){
    authenticate("jwt"){
        get("fitness-api/v1/exercises-in-workout-by-workoutPerDayId") {
            try {
                val workoutPerDayId = call.request.queryParameters["workoutPerDayId"]?.toIntOrNull()
                if (workoutPerDayId == null) {
                    call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
                    return@get
                }
                val exercises = exerciseInWorkoutUseCase.getExercisesByWorkoutPerDayId(workoutPerDayId)
                call.respond(exercises)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
            }
        }

        get("fitness-api/v1/all-exercises-in-workout") {
            try {
                val exercises = exerciseInWorkoutUseCase.getAllExercisesInWorkout()
                call.respond(exercises)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
            }
        }

        post("fitness-api/v1/exercise-in-workout") {
            try {
                val exercise = call.receive<ExerciseInWorkoutModel>()
                exerciseInWorkoutUseCase.createExerciseInWorkout(exercise)
                call.respond(HttpStatusCode.Created, BaseResponse(true, Constants.Success.CREATED))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
            }
        }

        put("fitness-api/v1/exercise-in-workout") {
            try {
                val exercise = call.receive<ExerciseInWorkoutModel>()
                exerciseInWorkoutUseCase.updateExerciseInWorkout(exercise)
                call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.UPDATED))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
            }
        }

        delete("fitness-api/v1/exercise-in-workout") {
            try {
                val id = call.request.queryParameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
                    return@delete
                }
                exerciseInWorkoutUseCase.deleteExerciseInWorkout(id)
                call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.DELETED))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
            }
        }
    }
}