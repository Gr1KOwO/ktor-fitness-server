package com.fitnessServIB.data.repository

import com.fitnessServIB.data.model.ExerciseToTypeModel
import com.fitnessServIB.data.tables.ExerciseToTypeTable
import com.fitnessServIB.domain.repository.ExerciseToTypeRepository
import com.fitnessServIB.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ExerciseToTypeRepositoryImpl: ExerciseToTypeRepository {
    override suspend fun getAllExerciseToTypes(): List<ExerciseToTypeModel> = dbQuery {
        ExerciseToTypeTable.selectAll().mapNotNull { rowToExerciseToType(it) }
    }
    override suspend fun getExerciseToTypeByExerciseId(exerciseId: Int): List<ExerciseToTypeModel> = dbQuery {
        ExerciseToTypeTable.select { ExerciseToTypeTable.exerciseId.eq(exerciseId) }
            .mapNotNull { rowToExerciseToType(it) }
    }

    override suspend fun getExerciseToTypeByExerciseTypeId(exerciseTypeId: Int): List<ExerciseToTypeModel> = dbQuery {
        ExerciseToTypeTable.select { ExerciseToTypeTable.exerciseTypeId.eq(exerciseTypeId) }
            .mapNotNull { rowToExerciseToType(it) }
    }

    override suspend fun createExerciseToType(exerciseToType: ExerciseToTypeModel) {
        dbQuery {
            ExerciseToTypeTable.insert { row ->
                row[exerciseId] = exerciseToType.exerciseId
                row[exerciseTypeId] = exerciseToType.exerciseTypeId
            }
        }
    }

    override suspend fun deleteExerciseToType(exerciseId: Int, exerciseTypeId: Int) {
        dbQuery {
            ExerciseToTypeTable.deleteWhere {
                (ExerciseToTypeTable.exerciseId eq exerciseId) and
                        (ExerciseToTypeTable.exerciseTypeId eq exerciseTypeId)
            }
        }
    }
    private fun rowToExerciseToType(row: ResultRow?): ExerciseToTypeModel? =
        if(row==null) null else ExerciseToTypeModel(
            exerciseId = row[ExerciseToTypeTable.exerciseId],
            exerciseTypeId = row[ExerciseToTypeTable.exerciseTypeId]
        )
}