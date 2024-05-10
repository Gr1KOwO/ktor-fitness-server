package com.fitnessServIB.data.repository

import com.fitnessServIB.data.model.FitnessLevelModel
import com.fitnessServIB.data.tables.FitnessLevelTable
import com.fitnessServIB.domain.repository.FitnessLevelRepository
import com.fitnessServIB.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class FitnessLevelRepositoryImpl : FitnessLevelRepository {
    override suspend fun getAllFitnessLevels(): List<FitnessLevelModel> = dbQuery {
        FitnessLevelTable.selectAll().map { rowToFitnessLevel(it) }
    }

    override suspend fun getFitnessLevelById(id: Int): FitnessLevelModel? = dbQuery {
        FitnessLevelTable.select { FitnessLevelTable.id.eq(id) }
            .mapNotNull { rowToFitnessLevel(it) }
            .singleOrNull()
    }

    override suspend fun createFitnessLevel(fitnessLevel: FitnessLevelModel) {
        dbQuery {
            FitnessLevelTable.insert { row ->
                row[name] = fitnessLevel.name
            }
        }
    }

    override suspend fun updateFitnessLevel(fitnessLevel: FitnessLevelModel) {
        dbQuery {
            FitnessLevelTable.update({ FitnessLevelTable.id.eq(fitnessLevel.id) }) { row ->
                row[name] = fitnessLevel.name
            }
        }
    }

    override suspend fun deleteFitnessLevel(id: Int) {
        dbQuery {
            FitnessLevelTable.deleteWhere { FitnessLevelTable.id.eq(id) }
        }
    }

    private fun rowToFitnessLevel(row: ResultRow): FitnessLevelModel =
        FitnessLevelModel(
            id = row[FitnessLevelTable.id],
            name = row[FitnessLevelTable.name]
        )
}