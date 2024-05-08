package com.fitnessServIB.data.repository

import com.fitnessServIB.data.model.ExerciseInWorkoutModel
import com.fitnessServIB.data.tables.ExercisesInWorkoutTable
import com.fitnessServIB.domain.repository.ExerciseInWorkoutRepository
import com.fitnessServIB.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ExerciseInWorkoutRepositoryImpl : ExerciseInWorkoutRepository {
    override suspend fun getExercisesByWorkoutPerDayId(workoutPerDayId: Int): List<ExerciseInWorkoutModel> = dbQuery {
        ExercisesInWorkoutTable.select { ExercisesInWorkoutTable.workoutPerDayId.eq(workoutPerDayId) }
            .map { rowToExerciseInWorkout(it) }
    }

    override suspend fun getAllExercisesInWorkout(): List<ExerciseInWorkoutModel> = dbQuery {
        ExercisesInWorkoutTable.selectAll()
            .map { rowToExerciseInWorkout(it) }
    }

    override suspend fun createExerciseInWorkout(exerciseInWorkout: ExerciseInWorkoutModel) {
        dbQuery {
            ExercisesInWorkoutTable.insert { row ->
                row[workoutPerDayId] = exerciseInWorkout.workoutPerDayId
                row[exerciseId] = exerciseInWorkout.exerciseId
                row[sets] = exerciseInWorkout.sets
                row[repetitions] = exerciseInWorkout.reps
                row[weight] = exerciseInWorkout.weight
                row[met] = exerciseInWorkout.met
                row[isCompleted] = exerciseInWorkout.isCompleted
                row[isSkipped] = exerciseInWorkout.isSkipped
            }
        }
    }

    override suspend fun updateExerciseInWorkout(exerciseInWorkout: ExerciseInWorkoutModel) {
        dbQuery {
            ExercisesInWorkoutTable.update({ ExercisesInWorkoutTable.id.eq(exerciseInWorkout.id) }) { row ->
                row[workoutPerDayId] = exerciseInWorkout.workoutPerDayId
                row[exerciseId] = exerciseInWorkout.exerciseId
                row[sets] = exerciseInWorkout.sets
                row[repetitions] = exerciseInWorkout.reps
                row[weight] = exerciseInWorkout.weight
                row[met] = exerciseInWorkout.met
                row[isCompleted] = exerciseInWorkout.isCompleted
                row[isSkipped] = exerciseInWorkout.isSkipped
            }
        }
    }

    override suspend fun deleteExerciseInWorkout(id: Int) {
        dbQuery {
            ExercisesInWorkoutTable.deleteWhere { ExercisesInWorkoutTable.id.eq(id) }
        }
    }

    private fun rowToExerciseInWorkout(row: ResultRow): ExerciseInWorkoutModel {
        return ExerciseInWorkoutModel(
            id = row[ExercisesInWorkoutTable.id],
            workoutPerDayId = row[ExercisesInWorkoutTable.workoutPerDayId],
            exerciseId = row[ExercisesInWorkoutTable.exerciseId],
            sets = row[ExercisesInWorkoutTable.sets],
            reps = row[ExercisesInWorkoutTable.repetitions],
            weight = row[ExercisesInWorkoutTable.weight],
            met = row[ExercisesInWorkoutTable.met],
            isCompleted = row[ExercisesInWorkoutTable.isCompleted],
            isSkipped = row[ExercisesInWorkoutTable.isSkipped]
        )
    }
}