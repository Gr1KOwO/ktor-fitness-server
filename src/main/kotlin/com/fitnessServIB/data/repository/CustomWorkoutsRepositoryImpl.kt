package com.fitnessServIB.data.repository

import com.fitnessServIB.data.model.CustomWorkoutModel
import com.fitnessServIB.data.tables.CustomWorkoutsTable
import com.fitnessServIB.domain.repository.CustomWorkoutsRepository
import com.fitnessServIB.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class CustomWorkoutsRepositoryImpl : CustomWorkoutsRepository {
    override suspend fun getAllCustomWorkouts(): List<CustomWorkoutModel> = dbQuery {
        CustomWorkoutsTable.selectAll().mapNotNull { rowToCustomWorkout(it) }
    }

    override suspend fun getCustomWorkoutById(id: Int): CustomWorkoutModel? = dbQuery {
        CustomWorkoutsTable.select { CustomWorkoutsTable.id.eq(id) }
            .mapNotNull { rowToCustomWorkout(it) }
            .singleOrNull()
    }

    override suspend fun createCustomWorkout(customWorkout: CustomWorkoutModel) {
        dbQuery {
            CustomWorkoutsTable.insert { row ->
                row[name] = customWorkout.name
                row[description] = customWorkout.description
                row[isPrivate] = customWorkout.isPrivate
            }
        }
    }

    override suspend fun updateCustomWorkout(customWorkout: CustomWorkoutModel) {
        dbQuery {
            CustomWorkoutsTable.update({ CustomWorkoutsTable.id.eq(customWorkout.id) }) { row ->
                row[name] = customWorkout.name
                row[description] = customWorkout.description
                row[isPrivate] = customWorkout.isPrivate
            }
        }
    }

    override suspend fun deleteCustomWorkout(id: Int) {
        dbQuery {
            CustomWorkoutsTable.deleteWhere { CustomWorkoutsTable.id.eq(id) }
        }
    }

    private fun rowToCustomWorkout(row: ResultRow?): CustomWorkoutModel? {
        if (row==null)return null
        return CustomWorkoutModel(
            id = row[CustomWorkoutsTable.id],
            name = row[CustomWorkoutsTable.name],
            description = row[CustomWorkoutsTable.description],
            isPrivate = row[CustomWorkoutsTable.isPrivate]
        )
    }
}