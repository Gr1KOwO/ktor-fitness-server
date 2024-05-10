package com.fitnessServIB.routes

import com.fitnessServIB.data.model.FoodModel
import com.fitnessServIB.data.response.BaseResponse
import com.fitnessServIB.domain.usecase.FoodUseCase
import com.fitnessServIB.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.FoodRoute(foodUseCase: FoodUseCase) {
    get("fitness-api/v1/food-list") {
        try {
            val foods = foodUseCase.getAllFoods()
            call.respond(HttpStatusCode.OK, foods)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
        }
    }

    get("fitness-api/v1/food") {
        val foodId = call.parameters["id"]?.toIntOrNull()
        if (foodId == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@get
        }
        try {
            val food = foodUseCase.getFoodById(foodId)
            if (food != null) {
                call.respond(HttpStatusCode.OK, food)
            } else {
                call.respond(HttpStatusCode.NotFound, BaseResponse(false, Constants.Error.NOT_FOUND))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
        }
    }

    get("fitness-api/v1/food-by-type") {
        val typeId = call.parameters["typeId"]?.toIntOrNull()
        if (typeId == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@get
        }
        try {
            val foods = foodUseCase.getFoodsByTypeId(typeId)
            call.respond(HttpStatusCode.OK, foods)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
        }
    }

    post("fitness-api/v1/create-food") {
        val food = call.receiveNullable<FoodModel>()?:kotlin.run {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.MISSING_FIELDS))
            return@post
        }
        try {
            foodUseCase.createFood(food)
            call.respond(HttpStatusCode.Created, BaseResponse(true, Constants.Success.CREATED))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
        }
    }
    put("fitness-api/v1/update-food") {
        val foodId = call.parameters["id"]?.toIntOrNull()
        if (foodId == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@put
        }
        val updatedFood = call.receiveOrNull<FoodModel>()
        if (updatedFood == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.MISSING_FIELDS))
            return@put
        }
        try {
            val existingFood = foodUseCase.getFoodById(foodId)
            if (existingFood == null) {
                call.respond(HttpStatusCode.NotFound, BaseResponse(false, Constants.Error.NOT_FOUND))
            } else {
                foodUseCase.updateFood(updatedFood.copy(id = foodId))
                call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.UPDATED))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
        }
    }

    delete("fitness-api/v1/del-food") {
        val foodId = call.parameters["id"]?.toIntOrNull()
        if (foodId == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@delete
        }
        try {
            val existingFood = foodUseCase.getFoodById(foodId)
            if (existingFood == null) {
                call.respond(HttpStatusCode.NotFound, BaseResponse(false, Constants.Error.NOT_FOUND))
            } else {
                foodUseCase.deleteFood(foodId)
                call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.DELETED))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
        }
    }
}