package com.fitnessServIB.routes

import com.fitnessServIB.data.model.ConsumedFoodModel
import com.fitnessServIB.data.model.UserModel
import com.fitnessServIB.domain.usecase.ConsumedFoodUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.datetime.LocalDate

fun Route.ConsumedFoodRoute(consumedFoodUseCase: ConsumedFoodUseCase){
    authenticate("jwt"){
        get("fitness-api/v1/consumed-foods") {
            try {
                val userId = call.principal<UserModel>()!!.userId
                val consumedFood = consumedFoodUseCase.getAllConsumedFood(userId)
                call.respond(HttpStatusCode.OK, consumedFood)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        get("fitness-api/v1/consumed-food-date") {
            try {
                val userId = call.principal<UserModel>()!!.userId
                val dateParam = call.request.queryParameters["date"]
                if(dateParam == null)
                {
                    call.respond(HttpStatusCode.OK)
                    return@get
                }
                val date = LocalDate.parse(dateParam)
                val consumedFood = consumedFoodUseCase.getConsumedFoodByDate(userId, date)
                call.respond(HttpStatusCode.OK, consumedFood)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        post("fitness-api/v1/create-consumed-food") {
            try {
                val userId = call.principal<UserModel>()!!.userId
                val consumedFood = call.receiveOrNull<ConsumedFoodModel>()
                if (consumedFood == null) {
                    call.respond(HttpStatusCode.BadRequest)
                } else {
                    consumedFoodUseCase.createConsumedFood(consumedFood.copy(userId = userId))
                    call.respond(HttpStatusCode.OK)
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        patch("fitness-api/v1/update-consumed-food") {
            try {
                val userId = call.principal<UserModel>()!!.userId
                val consumedFoodId = call.parameters["id"]?.toIntOrNull()
                val consumedFood = call.receiveOrNull<ConsumedFoodModel>()
                if (consumedFoodId == null || consumedFood == null) {
                    call.respond(HttpStatusCode.BadRequest)
                } else {
                    consumedFoodUseCase.updateConsumedFood(consumedFood.copy(id = consumedFoodId, userId = userId))
                    call.respond(HttpStatusCode.OK)
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        delete("fitness-api/v1/consumed-food-del") {
            try {
                val userId = call.principal<UserModel>()!!.userId
                val consumedFoodId = call.parameters["id"]?.toIntOrNull()
                if (consumedFoodId == null) {
                    call.respond(HttpStatusCode.BadRequest)
                } else {
                    val existingConsumedFood = consumedFoodUseCase.getConsumedFoodById(consumedFoodId)
                    if (existingConsumedFood == null || existingConsumedFood.userId != userId) {
                        call.respond(HttpStatusCode.NotFound)
                    } else {
                        consumedFoodUseCase.deleteConsumedFood(consumedFoodId)
                        call.respond(HttpStatusCode.OK)
                    }
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError)
            }
        }
    }
}