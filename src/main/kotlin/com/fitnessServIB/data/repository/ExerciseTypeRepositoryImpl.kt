package com.fitnessServIB.data.repository

import com.fitnessServIB.data.model.ExerciseTypeModel
import com.fitnessServIB.data.tables.ExerciseTypeTable
import com.fitnessServIB.domain.repository.ExerciseTypeRepository
import com.fitnessServIB.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ExerciseTypeRepositoryImpl: ExerciseTypeRepository {
    override suspend fun getExerciseType(id: Int): ExerciseTypeModel? {
        return dbQuery {
            ExerciseTypeTable.select { (ExerciseTypeTable.id.eq(id)) }
                .mapNotNull { rowToExerciseType(it) }
                .singleOrNull()
        }
    }
    override suspend fun getAllExerciseTypes(): List<ExerciseTypeModel> {
        return dbQuery {
            ExerciseTypeTable.selectAll()
                .mapNotNull { rowToExerciseType(it) }
        }
    }
    override suspend fun insertExerciseType(exerciseType: ExerciseTypeModel) {
        dbQuery {
            ExerciseTypeTable.insert {
                it[name] = exerciseType.name
            }
        }
    }
    override suspend fun updateExerciseType(exerciseType: ExerciseTypeModel) {
        dbQuery {
            ExerciseTypeTable.update({ ExerciseTypeTable.id.eq(exerciseType.id) }) {
                it[name] = exerciseType.name
            }
        }
    }
    override suspend fun deleteExerciseType(id: Int) {
        dbQuery {
            ExerciseTypeTable.deleteWhere { ExerciseTypeTable.id eq id }
        }
    }
    private fun rowToExerciseType(row: ResultRow?): ExerciseTypeModel? {
        if (row == null) return null
        return ExerciseTypeModel(
            id = row[ExerciseTypeTable.id],
            name = row[ExerciseTypeTable.name],
        )
    }
}