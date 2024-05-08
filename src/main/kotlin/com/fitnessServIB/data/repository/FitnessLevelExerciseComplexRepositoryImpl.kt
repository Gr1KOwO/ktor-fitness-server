package com.fitnessServIB.data.repository

import com.fitnessServIB.data.model.FitnessLevelExerciseComplexModel
import com.fitnessServIB.data.tables.FitnessLevelExerciseComplexTable
import com.fitnessServIB.domain.repository.FitnessLevelExerciseComplexRepository
import com.fitnessServIB.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class FitnessLevelExerciseComplexRepositoryImpl : FitnessLevelExerciseComplexRepository {
    override suspend fun getExerciseComplexesByFitnessLevelId(fitnessLevelId: Int): List<FitnessLevelExerciseComplexModel> = dbQuery {
        FitnessLevelExerciseComplexTable.select { FitnessLevelExerciseComplexTable.fitnessLevelId.eq(fitnessLevelId) }
            .mapNotNull { rowToFitnessLevelExerciseComplex(it) }
    }

    override suspend fun getFitnessLevelsByExerciseComplexId(exerciseComplexId: Int): List<FitnessLevelExerciseComplexModel> = dbQuery {
        FitnessLevelExerciseComplexTable.select { FitnessLevelExerciseComplexTable.exerciseComplexId.eq(exerciseComplexId) }
            .mapNotNull { rowToFitnessLevelExerciseComplex(it) }
    }

    override suspend fun createFitnessLevelExerciseComplex(fitnessLevelExerciseComplex: FitnessLevelExerciseComplexModel) {
        dbQuery {
            FitnessLevelExerciseComplexTable.insert {
                it[fitnessLevelId] = fitnessLevelExerciseComplex.fitnessLevelId
                it[exerciseComplexId] = fitnessLevelExerciseComplex.exerciseComplexId
            }
        }
    }

    override suspend fun deleteFitnessLevelExerciseComplex(fitnessLevelId: Int, exerciseComplexId: Int) {
        dbQuery {
            FitnessLevelExerciseComplexTable.deleteWhere {
                (FitnessLevelExerciseComplexTable.fitnessLevelId eq fitnessLevelId) and
                        (FitnessLevelExerciseComplexTable.exerciseComplexId eq exerciseComplexId)
            }
        }
    }
    private fun rowToFitnessLevelExerciseComplex(row: ResultRow?): FitnessLevelExerciseComplexModel? {
        if (row == null) return null
        return FitnessLevelExerciseComplexModel(
            fitnessLevelId = row[FitnessLevelExerciseComplexTable.fitnessLevelId],
            exerciseComplexId = row[FitnessLevelExerciseComplexTable.exerciseComplexId]
        )
    }
}