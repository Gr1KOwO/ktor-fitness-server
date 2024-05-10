package com.fitnessServIB.routes

import com.fitnessServIB.data.model.UserCustomWorkoutsModel
import com.fitnessServIB.data.response.BaseResponse
import com.fitnessServIB.domain.usecase.UserCustomWorkoutsUseCase
import com.fitnessServIB.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
fun Route.UserCustomWorkoutsRoute(userCustomWorkoutsUseCase: UserCustomWorkoutsUseCase){
    authenticate("jwt"){
        get("fitness-api/v1/CustomWorkout-byUserId"){
            val userId = call.parameters["userId"]?.toIntOrNull()
            if (userId == null) {
                call.respond(HttpStatusCode.BadRequest,BaseResponse(false,Constants.Error.INVALID_ID))
                return@get
            }
            try {
                val userCustomWorkouts = userCustomWorkoutsUseCase.getUserCustomWorkoutsByUserId(userId)
                call.respond(HttpStatusCode.OK,userCustomWorkouts)
            }catch (e:Exception){
                call.respond(HttpStatusCode.BadRequest,BaseResponse(false,Constants.Error.GENERAL))
            }
        }
        get("fitness-api/v1/Users-by-CustomWorkoutById"){
            val customWorkoutsId = call.parameters["customWorkoutsId"]?.toIntOrNull()
            if (customWorkoutsId == null) {
                call.respond(HttpStatusCode.BadRequest,BaseResponse(false,Constants.Error.INVALID_ID))
                return@get
            }
            try {
                val userCustomWorkouts =
                    userCustomWorkoutsUseCase.getCustomWorkoutsByUserCustomWorkoutsId(customWorkoutsId)
                call.respond(HttpStatusCode.OK,userCustomWorkouts)
            }catch (e:Exception)
            {
                call.respond(HttpStatusCode.BadRequest,BaseResponse(false,Constants.Error.GENERAL))
            }
        }
        put("fitness-api/v1/UserCustomWorkout"){
            try {
                val userCustomWorkout = call.receive<UserCustomWorkoutsModel>()
                userCustomWorkoutsUseCase.addUserCustomWorkout(userCustomWorkout)
                call.respond(HttpStatusCode.OK,BaseResponse(true,Constants.Success.CREATED))
            }catch (e:Exception){
                call.respond(HttpStatusCode.BadRequest,BaseResponse(false,Constants.Error.GENERAL))
            }
        }
        delete("fitness-api/v1/del-UserCustomWorkout") {
            try {
                val userCustomWorkout = call.receive<UserCustomWorkoutsModel>()
                userCustomWorkoutsUseCase.removeUserCustomWorkout(userCustomWorkout)
                call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.DELETED))
            }catch (e:Exception)
            {
                call.respond(HttpStatusCode.BadRequest,BaseResponse(false,Constants.Error.GENERAL))
            }
        }
    }
}