package com.fitnessServIB.data.repository

import com.fitnessServIB.data.model.ExerciseModel
import com.fitnessServIB.data.tables.ExerciseTable
import com.fitnessServIB.domain.repository.ExerciseRepository
import com.fitnessServIB.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ExerciseRepositoryImpl : ExerciseRepository {
    override suspend fun getAllExercises():List<ExerciseModel> = dbQuery {
        ExerciseTable.selectAll().mapNotNull { rowToExercise(it) }
    }

    override suspend fun getExerciseById(id: Int):  ExerciseModel? = dbQuery {
        ExerciseTable.select { ExerciseTable.id eq id }.mapNotNull { rowToExercise(it) }.singleOrNull()
    }

    override suspend fun createExercise(exercise: ExerciseModel) {
        dbQuery {
            ExerciseTable.insert { table ->
                table[name] = exercise.name
                table[description] = exercise.description
                table[medicalContraindications] = exercise.medicalContraindications
                table[urlGif] = exercise.urlGif
            }
        }
    }

    override suspend fun updateExercise(exercise: ExerciseModel) {
        dbQuery {
            ExerciseTable.update({ ExerciseTable.id eq exercise.id }) { table ->
                table[name] = exercise.name
                table[description] = exercise.description
                table[medicalContraindications] = exercise.medicalContraindications
                table[urlGif] = exercise.urlGif
            }
        }
    }

    override suspend fun deleteExercise(id: Int) {
        dbQuery {
            ExerciseTable.deleteWhere { ExerciseTable.id eq id }
        }
    }
    private fun rowToExercise(row: ResultRow?): ExerciseModel? {
        if (row == null) return null
        return ExerciseModel(
            id = row[ExerciseTable.id],
            name = row[ExerciseTable.name],
            description = row[ExerciseTable.description],
            medicalContraindications = row[ExerciseTable.medicalContraindications],
            urlGif = row[ExerciseTable.urlGif]
        )
    }
}