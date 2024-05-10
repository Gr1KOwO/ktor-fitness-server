package com.fitnessServIB

import com.fitnessServIB.authentification.JwtService
import com.fitnessServIB.data.repository.*
import com.fitnessServIB.domain.usecase.*
import com.fitnessServIB.plugins.*
import com.fitnessServIB.plugins.DatabaseFactory.initializationDataBase
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val jwtServices = JwtService()
    //repositories
    val userRepository = UserRepositoryImpl()
    val foodRepository = FoodRepositoryImpl()
    val foodTypeRepositoryImpl = FoodTypeRepositoryImpl()
    val dishesRepositoryImpl = DishRepositoryImpl()
    val consumedFoodRepositoryImpl = ConsumedFoodRepositoryImpl()
    val exerciseTypeRepositoryImpl = ExerciseTypeRepositoryImpl()
    val exerciseRepositoryImpl = ExerciseRepositoryImpl()
    val exerciseToTypeRepositoryImpl = ExerciseToTypeRepositoryImpl()
    val fitnessLevelRepositoryImpl = FitnessLevelRepositoryImpl()
    val exerciseComplexRepositoryImpl = ExerciseComplexRepositoryImpl()
    val fitnessLevelExerciseComplexRepositoryImpl = FitnessLevelExerciseComplexRepositoryImpl()
    val customWorkoutsRepositoryImpl = CustomWorkoutsRepositoryImpl()
    val userCustomWorkoutsRepositoryImpl = UserCustomWorkoutsRepositoryImpl()
    val userExerciseRepositoryImpl = UserExerciseComplexRepositoryImpl()
    val statisticsRepositoryImpl = StatisticsRepositoryImpl()
    val exerciseInWorkoutRepositoryImpl = ExerciseInWorkoutRepositoryImpl()
    val workoutPerDayRepositoryImpl = WorkoutPerDayRepositoryImpl()

    //use-cases
    val userUseCase = UserUseCase(userRepository,jwtServices)
    val foodUseCase = FoodUseCase(foodRepository)
    val foodTypeUseCase = FoodTypeUseCase(foodTypeRepositoryImpl)
    val dishesUseCase = DishUseCase(dishesRepositoryImpl)
    val consumedFoodUseCase = ConsumedFoodUseCase(consumedFoodRepositoryImpl)
    val exerciseTypeUseCase = ExerciseTypeUseCase(exerciseTypeRepositoryImpl)
    val exerciseUseCase = ExerciseUseCase(exerciseRepositoryImpl)
    val exerciseToTypeUseCase = ExerciseToTypeUseCase(exerciseToTypeRepositoryImpl)
    val fitnessLevelUseCase = FitnessLevelUseCase(fitnessLevelRepositoryImpl)
    val exerciseComplexUseCase = ExerciseComplexUseCase(exerciseComplexRepositoryImpl)
    val fitnessLevelExerciseComplexUseCase = FitnessLevelExerciseComplexUseCase(fitnessLevelExerciseComplexRepositoryImpl)
    val customWorkoutsUseCase = CustomWorkoutsUseCase(customWorkoutsRepositoryImpl)
    val userCustomWorkoutsUseCase = UserCustomWorkoutsUseCase(userCustomWorkoutsRepositoryImpl)
    val userExerciseComplexUseCase = UserExerciseComplexUseCase(userExerciseRepositoryImpl)
    val statisticsUseCase = StatisticsUseCase(statisticsRepositoryImpl)
    val exerciseInWorkoutUseCase = ExerciseInWorkoutUseCase(exerciseInWorkoutRepositoryImpl)
    val workoutPerDayUseCase = WorkoutPerDayUseCase(workoutPerDayRepositoryImpl)

    configureSerialization()
    initializationDataBase()
    configureHTTP()
//    configureSockets()
    configureMonitoring()
    configureSecurity(userUseCase)
    configureRouting(
        userUseCase,
        foodUseCase,
        foodTypeUseCase,
        dishesUseCase,
        consumedFoodUseCase,
        exerciseTypeUseCase,
        exerciseUseCase,
        exerciseToTypeUseCase,
        fitnessLevelUseCase,
        exerciseComplexUseCase,
        fitnessLevelExerciseComplexUseCase,
        customWorkoutsUseCase,
        userCustomWorkoutsUseCase,
        userExerciseComplexUseCase,
        statisticsUseCase,
        exerciseInWorkoutUseCase,
        workoutPerDayUseCase,
    )
}
