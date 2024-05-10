package com.fitnessServIB.data.repository

import com.fitnessServIB.data.model.WorkoutPerDayModel
import com.fitnessServIB.data.tables.WorkoutPerDayTable
import com.fitnessServIB.domain.repository.WorkoutPerDayRepository
import com.fitnessServIB.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class WorkoutPerDayRepositoryImpl : WorkoutPerDayRepository {
    override suspend fun getWorkoutPerDayById(id: Int): WorkoutPerDayModel? = dbQuery {
        WorkoutPerDayTable.select { WorkoutPerDayTable.id eq id }
            .mapNotNull { rowToWorkoutPerDay(it) }
            .singleOrNull()
    }

    override suspend fun getAllWorkoutsPerDay(): List<WorkoutPerDayModel> = dbQuery {
        WorkoutPerDayTable.selectAll()
            .mapNotNull { rowToWorkoutPerDay(it) }
    }

    override suspend fun createWorkoutPerDay(workoutPerDay: WorkoutPerDayModel) {
        dbQuery {
            WorkoutPerDayTable.insert {
                it[date] = workoutPerDay.date
                it[exerciseComplexId] = workoutPerDay.exerciseComplex
                it[customWorkoutId] = workoutPerDay.customWorkout
                it[isCompleted] = workoutPerDay.isCompleted
                it[isSkipped] = workoutPerDay.isSkipped
            }
        }
    }

    override suspend fun updateWorkoutPerDay(workoutPerDay: WorkoutPerDayModel) {
        dbQuery {
            WorkoutPerDayTable.update({ WorkoutPerDayTable.id eq workoutPerDay.id }) {
                it[date] = workoutPerDay.date
                it[exerciseComplexId] = workoutPerDay.exerciseComplex
                it[customWorkoutId] = workoutPerDay.customWorkout
                it[isCompleted] = workoutPerDay.isCompleted
                it[isSkipped] = workoutPerDay.isSkipped
            }
        }
    }

    override suspend fun deleteWorkoutPerDay(id: Int) {
        dbQuery {
            WorkoutPerDayTable.deleteWhere { WorkoutPerDayTable.id eq id }
        }
    }

    private fun rowToWorkoutPerDay(row: ResultRow?): WorkoutPerDayModel? {
        if (row == null) return null
        return WorkoutPerDayModel(
            id = row[WorkoutPerDayTable.id],
            date = row[WorkoutPerDayTable.date],
            exerciseComplex = row[WorkoutPerDayTable.exerciseComplexId],
            customWorkout = row[WorkoutPerDayTable.customWorkoutId],
            isCompleted = row[WorkoutPerDayTable.isCompleted],
            isSkipped = row[WorkoutPerDayTable.isSkipped],
        )
    }
}