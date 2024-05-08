package com.fitnessServIB.data.repository

import com.fitnessServIB.data.model.UserCustomWorkoutsModel
import com.fitnessServIB.data.tables.UserCustomWorkoutsTable
import com.fitnessServIB.domain.repository.UserCustomWorkoutsRepository
import com.fitnessServIB.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class UserCustomWorkoutsRepositoryImpl : UserCustomWorkoutsRepository {
    override suspend fun getUserCustomWorkoutsByUserId(userId: String): List<UserCustomWorkoutsModel> = dbQuery {
        UserCustomWorkoutsTable.select { UserCustomWorkoutsTable.userId.eq(userId) }
            .mapNotNull { rowToUserCustomWorkouts(it) }
    }

    override suspend fun getCustomWorkoutsByUserCustomWorkoutsId(customWorkoutsId: Int): List<UserCustomWorkoutsModel> = dbQuery {
        UserCustomWorkoutsTable.select { UserCustomWorkoutsTable.customWorkoutsId.eq(customWorkoutsId) }
            .mapNotNull { rowToUserCustomWorkouts(it) }
    }

    override suspend fun addUserCustomWorkout(userCustomWorkoutsModel: UserCustomWorkoutsModel) {
        dbQuery {
            UserCustomWorkoutsTable.insert { row ->
                row[userId] = userCustomWorkoutsModel.userId
                row[customWorkoutsId] = userCustomWorkoutsModel.customWorkoutsId
            }
        }
    }

    override suspend fun removeUserCustomWorkout(userCustomWorkoutsModel: UserCustomWorkoutsModel) {
        dbQuery {
            UserCustomWorkoutsTable.deleteWhere { (UserCustomWorkoutsTable.userId eq userCustomWorkoutsModel.userId) and
                    (UserCustomWorkoutsTable.customWorkoutsId eq userCustomWorkoutsModel.customWorkoutsId) }
        }
    }

    private fun rowToUserCustomWorkouts(row: ResultRow?): UserCustomWorkoutsModel? {
        if (row == null) return null
        return UserCustomWorkoutsModel(
            userId = row[UserCustomWorkoutsTable.userId],
            customWorkoutsId = row[UserCustomWorkoutsTable.customWorkoutsId]
        )
    }
}