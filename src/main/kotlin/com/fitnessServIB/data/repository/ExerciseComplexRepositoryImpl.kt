package com.fitnessServIB.data.repository

import com.fitnessServIB.data.model.ExerciseComplexModel
import com.fitnessServIB.data.tables.ExerciseComplexTable
import com.fitnessServIB.domain.repository.ExerciseComplexRepository
import com.fitnessServIB.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ExerciseComplexRepositoryImpl : ExerciseComplexRepository {
    override suspend fun getAllExerciseComplexes(): List<ExerciseComplexModel> = dbQuery {
        ExerciseComplexTable.selectAll().map { rowToExerciseComplex(it) }
    }

    override suspend fun getExerciseComplexById(id: Int): ExerciseComplexModel? = dbQuery {
        ExerciseComplexTable.select { ExerciseComplexTable.id.eq(id) }
            .mapNotNull { rowToExerciseComplex(it) }
            .singleOrNull()
    }

    override suspend fun createExerciseComplex(exerciseComplex: ExerciseComplexModel) {
        dbQuery {
            ExerciseComplexTable.insert { row ->
                row[name] = exerciseComplex.name
                row[description] = exerciseComplex.description
            }
        }
    }

    override suspend fun updateExerciseComplex(exerciseComplex: ExerciseComplexModel) {
        dbQuery {
            ExerciseComplexTable.update({ ExerciseComplexTable.id.eq(exerciseComplex.id) }) { row ->
                row[name] = exerciseComplex.name
                row[description] = exerciseComplex.description
            }
        }
    }

    override suspend fun deleteExerciseComplex(id: Int) {
        dbQuery {
            ExerciseComplexTable.deleteWhere { ExerciseComplexTable.id.eq(id) }
        }
    }

    private fun rowToExerciseComplex(row: ResultRow): ExerciseComplexModel =
        ExerciseComplexModel(
            id = row[ExerciseComplexTable.id],
            name = row[ExerciseComplexTable.name],
            description = row[ExerciseComplexTable.description]
        )
}