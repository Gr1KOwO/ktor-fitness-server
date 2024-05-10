package com.fitnessServIB.routes

import com.fitnessServIB.data.model.StatisticsModel
import com.fitnessServIB.data.response.BaseResponse
import com.fitnessServIB.domain.usecase.StatisticsUseCase
import com.fitnessServIB.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.datetime.LocalDate

fun Route.StatisticsRoute(statisticsUseCase: StatisticsUseCase){
    authenticate("jwt"){
        get("fitness-api/v1/statistics-by-user-and-date") {
            try {
                val userId = call.parameters["userId"]?.toIntOrNull()
                val dateString = call.parameters["date"]
                val date = dateString?.let { LocalDate.parse(it) }
                if (userId == null || date == null) {
                    call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.GENERAL))
                    return@get
                }
                val statistics = statisticsUseCase.getStatisticsByUserAndDate(userId, date)
                if (statistics != null) {
                    call.respond(statistics)
                } else {
                    call.respond(HttpStatusCode.NotFound, BaseResponse(false, Constants.Error.NOT_FOUND))
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
            }
        }

        get("fitness-api/v1/statistics-by-user") {
            try {
                val userId = call.request.queryParameters["userId"]?.toIntOrNull()
                if (userId == null) {
                    call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
                    return@get
                }
                val statisticsList = statisticsUseCase.getAllStatisticsForUser(userId)
                call.respond(statisticsList)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
            }
        }

        post("fitness-api/v1/statistics") {
            try {
                val statistics = call.receive<StatisticsModel>()
                statisticsUseCase.createStatistics(statistics)
                call.respond(HttpStatusCode.Created, BaseResponse(true, Constants.Success.CREATED))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
            }
        }

        put("fitness-api/v1/statistics") {
            try {
                val statistics = call.receive<StatisticsModel>()
                statisticsUseCase.updateStatistics(statistics)
                call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.UPDATED))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
            }
        }

        delete("fitness-api/v1/statistics") {
            try {
                val id = call.request.queryParameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, BaseResponse(false, Constants.Error.INVALID_ID))
                    return@delete
                }
                statisticsUseCase.deleteStatistics(id)
                call.respond(HttpStatusCode.OK, BaseResponse(true, Constants.Success.DELETED))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, Constants.Error.GENERAL))
            }
        }
    }
}