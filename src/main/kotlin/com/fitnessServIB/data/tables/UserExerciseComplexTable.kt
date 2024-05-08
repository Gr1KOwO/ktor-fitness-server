package com.fitnessServIB.data.tables

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object UserExerciseComplexTable: Table() {
    val userId = varchar("userId",512).references(UserTable.email,onDelete = ReferenceOption.CASCADE,onUpdate = ReferenceOption.CASCADE)
    val exerciseComplexId = integer("exerciseComplexId").references(ExerciseComplexTable.id,onDelete = ReferenceOption.CASCADE,onUpdate = ReferenceOption.CASCADE)
    override val primaryKey = PrimaryKey(userId, exerciseComplexId)
}