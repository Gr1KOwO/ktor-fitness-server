package com.fitnessServIB.routes

import com.fitnessServIB.data.model.WorkoutPerDayModel
import com.fitnessServIB.data.response.BaseResponse
import com.fitnessServIB.domain.usecase.WorkoutPerDayUseCase
import com.fitnessServIB.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.WorkoutPerDayRoute(workoutPerDayUseCase: WorkoutPerDayUseCase){
    get("fitness-api/v1/workout-per-day-by-id") {
        try {
            val id = call.request.queryParameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
                return@get
            }
            val workoutPerDay = workoutPerDayUseCase.getWorkoutPerDayById(id)
            if (workoutPerDay == null) {
                call.respond(HttpStatusCode.NotFound, BaseResponse(false, Constants.Error.NOT_FOUND))
            } else {
                call.respond(workoutPerDay)
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
        }
    }

    get("fitness-api/v1/all-workouts-per-day") {
        try {
            val workoutsPerDay = workoutPerDayUseCase.getAllWorkoutsPerDay()
            call.respond(workoutsPerDay)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
        }
    }

    post("fitness-api/v1/workout-per-day") {
        try {
            val workoutPerDay = call.receive<WorkoutPerDayModel>()
            workoutPerDayUseCase.createWorkoutPerDay(workoutPerDay)
            call.respond(HttpStatusCode.Created, BaseResponse(true, Constants.Success.CREATED))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
        }
    }

    put("fitness-api/v1/workout-per-day") {
        try {
            val workoutPerDay = call.receive<WorkoutPerDayModel>()
            workoutPerDayUseCase.updateWorkoutPerDay(workoutPerDay)
            call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.UPDATED))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
        }
    }

    delete("fitness-api/v1/workout-per-day") {
        try {
            val id = call.request.queryParameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
                return@delete
            }
            workoutPerDayUseCase.deleteWorkoutPerDay(id)
            call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.DELETED))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
        }
    }
}