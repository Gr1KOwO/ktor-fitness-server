package com.fitnessServIB.routes

import com.fitnessServIB.authentification.hash
import com.fitnessServIB.data.model.*
import com.fitnessServIB.data.requests.*
import com.fitnessServIB.data.response.*
import com.fitnessServIB.domain.usecase.*
import com.fitnessServIB.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun  Route.UserRoute(userUseCase: UserUseCase) {
    val hashFunction = { p: String  -> hash(password = p) }
    post("fitness-api/v1/signup"){
        val registerRequest =  call.receiveNullable<RegisterRequest>()?: kotlin.run{
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false,Constants.Error.GENERAL))
            return@post
        }
        try{
            val user = UserModel(
                userId = 0,
                email = registerRequest.email.trim().lowercase(),
                age = registerRequest.age,
                fullName = registerRequest.fullName,
                password = hashFunction(registerRequest.password.trim()),
                weight = registerRequest.weight,
                height = registerRequest.height,
                sex = registerRequest.sex
            )

            userUseCase.createdUser(user)
            call.respond(HttpStatusCode.OK,BaseResponse(true,userUseCase.generateToken(user)))

        }catch (e:Exception){
            call.respond(HttpStatusCode.Conflict,BaseResponse(false,e.message?:Constants.Error.GENERAL))
        }
    }

    post("fitness-api/v1/signin"){
        val loginRequest = call.receiveNullable<LoginRequest>()?:kotlin.run {
            call.respond(HttpStatusCode.BadRequest, BaseResponse(false,Constants.Error.GENERAL))
            return@post
        }
        try {
            val user = userUseCase.getUserByEmail(loginRequest.email.trim().lowercase())

            if(user==null){
                call.respond(HttpStatusCode.BadRequest,BaseResponse(false,Constants.Error.WRONG_EMAIL))
            }
            else{
                if(user.password == hashFunction(loginRequest.password.trim())){
                    val userResponse = UserResponse(
                        userId = user.userId,
                        fullName = user.fullName,
                        sex = user.sex,
                        age = user.age,
                        height = user.height,
                        weight = user.weight
                    )
                    call.respond(HttpStatusCode.OK, AuthResponse(true,userUseCase.generateToken(user),userResponse))
                }
                else{
                    call.respond(HttpStatusCode.BadRequest,BaseResponse(false,Constants.Error.INCORRECT_PASSWORD))
                }
            }
        }
        catch (e:Exception){
            call.respond(HttpStatusCode.Conflict,BaseResponse(false,e.message?:Constants.Error.GENERAL))
        }
    }
    authenticate("jwt") {
        delete("fitness-api/v1/delete-user"){
            try {
                val userId = call.principal<UserModel>()!!.userId
                userUseCase.deleteUser(userId)
                call.respond(HttpStatusCode.OK, BaseResponse(success = true, message = Constants.Success.USER_DELETED_SUCCESSFULLY))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.Conflict, BaseResponse(false, e.message ?: Constants.Error.GENERAL))
            }
        }

        patch("fitness-api/v1/update-user")
        {
            val updateUserRequest = call.receiveNullable<UpdateUserRequest>()?:kotlin.run {
                call.respond(HttpStatusCode.BadRequest, BaseResponse(false,Constants.Error.GENERAL))
                return@patch
            }

            try {
                val currentUser = call.principal<UserModel>()
                if(currentUser == null){
                    call.respond(HttpStatusCode.NotFound, BaseResponse(false, "User not found"))
                    return@patch
                }

                if (updateUserRequest.fullName == null &&
                    updateUserRequest.age == null && updateUserRequest.weight == null &&
                    updateUserRequest.height == null && updateUserRequest.sex == null &&
                    updateUserRequest.password == null
                ) {
                    call.respond(HttpStatusCode.BadRequest, BaseResponse(false, "At least one parameter must be provided for update"))
                    return@patch
                }

                val updatedUser = currentUser.copy(
                    fullName = updateUserRequest.fullName ?: currentUser.fullName,
                    age = updateUserRequest.age ?: currentUser.age,
                    weight = updateUserRequest.weight ?: currentUser.weight,
                    height = updateUserRequest.height ?: currentUser.height,
                    sex = updateUserRequest.sex ?: currentUser.sex,
                    password = updateUserRequest.password?.let { hashFunction(it) } ?: currentUser.password
                )
                userUseCase.updateUser(updatedUser)
                call.respond(HttpStatusCode.OK, BaseResponse(true, "User updated successfully"))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, BaseResponse(false, e.message ?: "Failed to update user"))
            }
        }
    }
}