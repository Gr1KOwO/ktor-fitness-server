package com.fitnessServIB.routes

import com.fitnessServIB.data.model.DishModel
import com.fitnessServIB.data.response.BaseResponse
import com.fitnessServIB.domain.usecase.DishUseCase
import com.fitnessServIB.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.DishesRoute(dishUseCase: DishUseCase){
    // Получить все блюда
    get("fitness-api/v1/dishes") {
        try {
            val dishes = dishUseCase.getAllDishes()
            call.respond(HttpStatusCode.OK, dishes)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
        }
    }

    // Получить блюдо по ID
    get("fitness-api/v1/dish") {
        val dishId = call.parameters["id"]?.toIntOrNull()
        if (dishId == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@get
        }
        try {
            val dish = dishUseCase.getDishById(dishId)
            if (dish != null) {
                call.respond(HttpStatusCode.OK, dish)
            } else {
                call.respond(HttpStatusCode.NotFound, BaseResponse(false, Constants.Error.NOT_FOUND))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
        }
    }

    // Создать новое блюдо
    post("fitness-api/v1/create-dish") {
        val request = call.receiveNullable<DishModel>() ?: run {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.MISSING_FIELDS))
            return@post
        }
        try {
            dishUseCase.createDish(request)
            call.respond(HttpStatusCode.OK, BaseResponse(true, "Dish created successfully"))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
        }
    }

    // Обновить блюдо
    put("fitness-api/v1/update-dish") {
        val dishId = call.parameters["id"]?.toIntOrNull()
        if (dishId == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@put
        }
        val request = call.receiveNullable<DishModel>() ?: run {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.MISSING_FIELDS))
            return@put
        }
        try {
            val existingDish = dishUseCase.getDishById(dishId)
            if (existingDish != null) {
                dishUseCase.updateDish(request.copy(id = dishId))
                call.respond(HttpStatusCode.OK, BaseResponse(true, "Dish updated successfully"))
            } else {
                call.respond(HttpStatusCode.NotFound, BaseResponse(false, Constants.Error.NOT_FOUND))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
        }
    }

    // Удалить блюдо
    delete("fitness-api/v1/del-dish") {
        val dishId = call.parameters["id"]?.toIntOrNull()
        if (dishId == null) {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
            return@delete
        }
        try {
            val existingDish = dishUseCase.getDishById(dishId)
            if (existingDish != null) {
                dishUseCase.deleteDish(dishId)
                call.respond(HttpStatusCode.OK, BaseResponse(true, "Dish deleted successfully"))
            } else {
                call.respond(HttpStatusCode.NotFound, BaseResponse(false, Constants.Error.NOT_FOUND))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
        }
    }
}