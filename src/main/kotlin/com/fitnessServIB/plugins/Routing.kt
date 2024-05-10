package com.fitnessServIB.plugins

import com.fitnessServIB.domain.usecase.*
import com.fitnessServIB.routes.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    userUseCase: UserUseCase,
    foodUseCase: FoodUseCase,
    foodTypeUseCase: FoodTypeUseCase,
    dishUseCase: DishUseCase,
    consumedFoodUseCase: ConsumedFoodUseCase,
    exerciseTypeUseCase:ExerciseTypeUseCase,
    exerciseUseCase:ExerciseUseCase,
    exerciseToTypeUseCase:ExerciseToTypeUseCase,
    fitnessLevelUseCase: FitnessLevelUseCase,
    exerciseComplexUseCase: ExerciseComplexUseCase,
    fitnessLevelExerciseComplexUseCase:FitnessLevelExerciseComplexUseCase,
    customWorkoutsUseCase:CustomWorkoutsUseCase,
    userCustomWorkoutsUseCase:UserCustomWorkoutsUseCase,
    userExerciseComplexUseCase:UserExerciseComplexUseCase,
    statisticsUseCase:StatisticsUseCase,
    exerciseInWorkoutUseCase:ExerciseInWorkoutUseCase,
    workoutPerDayUseCase:WorkoutPerDayUseCase,
    ) {
    routing {
        UserRoute(userUseCase = userUseCase)
        FoodTypeRoute(foodTypeUseCase=foodTypeUseCase)
        FoodRoute(foodUseCase = foodUseCase)
        DishesRoute(dishUseCase = dishUseCase)
        ConsumedFoodRoute(consumedFoodUseCase = consumedFoodUseCase)
        ExerciseTypeRoute(exerciseTypeUseCase=exerciseTypeUseCase)
        ExerciseRoute(exerciseUseCase=exerciseUseCase)
        ExerciseToTypeModel(exerciseToTypeUseCase)
        FitnessLevelRoute(fitnessLevelUseCase)
        ExerciseComplexRoute(exerciseComplexUseCase)
        FitnessLevelExerciseComplexRoute(fitnessLevelExerciseComplexUseCase)
        CustomWorkoutsRoute(customWorkoutsUseCase)
        UserCustomWorkoutsRoute(userCustomWorkoutsUseCase)
        UserExerciseComplexRoute(userExerciseComplexUseCase)
        StatisticsRoute(statisticsUseCase)
        ExerciseInWorkoutRoute(exerciseInWorkoutUseCase)
        WorkoutPerDayRoute(workoutPerDayUseCase)
    }
}
