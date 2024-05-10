package com.fitnessServIB.routes

import com.fitnessServIB.data.model.FoodTypeModel
import com.fitnessServIB.data.response.BaseResponse
import com.fitnessServIB.domain.usecase.FoodTypeUseCase
import com.fitnessServIB.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.FoodTypeRoute(foodTypeUseCase: FoodTypeUseCase) {
    // Получить все типы продуктов
    get("fitness-api/v1/food-types") {
        try {
            val foodTypes = foodTypeUseCase.getAllFoodTypes()
            call.respond(HttpStatusCode.OK, foodTypes)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
        }
    }

    // Получить тип продукта по идентификатору
    get("fitness-api/v1/food-type") {
        val typeId = call.parameters["id"]?.toIntOrNull()
        if (typeId == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@get
        }
        try {
            val foodType = foodTypeUseCase.getFoodTypeById(typeId)
            if (foodType != null) {
                call.respond(HttpStatusCode.OK, foodType)
            } else {
                call.respond(HttpStatusCode.NotFound, BaseResponse(false, Constants.Error.NOT_FOUND))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
        }
    }

    // Создать новый тип продукта
    post("fitness-api/v1/create-food-type") {
        val request = call.receiveNullable<FoodTypeModel>() ?: run {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.MISSING_FIELDS))
            return@post
        }
        try {
            foodTypeUseCase.createFoodType(request)
            call.respond(HttpStatusCode.OK, BaseResponse(true, "Food type created successfully"))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
        }
    }

    // Обновить тип продукта
    put("fitness-api/v1/update-food-type") {
        val typeId = call.parameters["id"]?.toIntOrNull()
        if (typeId == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@put
        }
        val request = call.receiveNullable<FoodTypeModel>() ?: run {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.MISSING_FIELDS))
            return@put
        }
        try {
            val existingFoodType = foodTypeUseCase.getFoodTypeById(typeId)
            if (existingFoodType != null) {
                foodTypeUseCase.updateFoodType(request.copy(id = typeId))
                call.respond(HttpStatusCode.OK, BaseResponse(true, "Food type updated successfully"))
            } else {
                call.respond(HttpStatusCode.NotFound, BaseResponse(false, Constants.Error.NOT_FOUND))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
        }
    }

    // Удалить тип продукта
    delete("fitness-api/v1/del-food-type") {
        val typeId = call.parameters["id"]?.toIntOrNull()
        if (typeId == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@delete
        }
        try {
            val existingFoodType = foodTypeUseCase.getFoodTypeById(typeId)
            if (existingFoodType != null) {
                foodTypeUseCase.deleteFoodType(typeId)
                call.respond(HttpStatusCode.OK, BaseResponse(true, "Food type deleted successfully"))
            } else {
                call.respond(HttpStatusCode.NotFound, BaseResponse(false, Constants.Error.NOT_FOUND))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
        }
    }
}