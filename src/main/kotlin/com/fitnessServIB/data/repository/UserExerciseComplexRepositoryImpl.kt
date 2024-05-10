package com.fitnessServIB.data.repository

import com.fitnessServIB.data.model.UserExerciseComplexModel
import com.fitnessServIB.data.tables.UserExerciseComplexTable
import com.fitnessServIB.domain.repository.UserExerciseComplexRepository
import com.fitnessServIB.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class UserExerciseComplexRepositoryImpl : UserExerciseComplexRepository {
    override suspend fun getUserExerciseComplexByUserId(userId: Int): List<UserExerciseComplexModel> = dbQuery {
        UserExerciseComplexTable.select { UserExerciseComplexTable.userId.eq(userId) }
            .mapNotNull { rowToUserExerciseComplex(it) }
    }

    override suspend fun getUserExerciseComplexByExerciseComplexId(exerciseComplexId: Int): List<UserExerciseComplexModel> =
        dbQuery {
            UserExerciseComplexTable.select { UserExerciseComplexTable.exerciseComplexId.eq(exerciseComplexId) }
                .mapNotNull { rowToUserExerciseComplex(it) }
        }

    override suspend fun createUserExerciseComplex(userExerciseComplex: UserExerciseComplexModel) {
        dbQuery {
            UserExerciseComplexTable.insert {
                it[userId] = userExerciseComplex.userId
                it[exerciseComplexId] = userExerciseComplex.exerciseComplexId
            }
        }
    }

    override suspend fun deleteUserExerciseComplex(userId: Int, exerciseComplexId: Int) {
        dbQuery {
            UserExerciseComplexTable.deleteWhere {
                (UserExerciseComplexTable.userId eq userId) and
                        (UserExerciseComplexTable.exerciseComplexId eq exerciseComplexId)
            }
        }
    }

    private fun rowToUserExerciseComplex(row: ResultRow?): UserExerciseComplexModel? {
        if (row == null) return null
        return UserExerciseComplexModel(
            userId = row[UserExerciseComplexTable.userId],
            exerciseComplexId = row[UserExerciseComplexTable.exerciseComplexId]
        )
    }
}