package com.fitnessServIB.data.tables

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object ExerciseToTypeTable:Table() {
    val exerciseId = integer("exerciseId").references(ExerciseTable.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val exerciseTypeId = integer("exerciseTypeId").references(ExerciseTypeTable.id,onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    override val primaryKey = PrimaryKey(exerciseId, exerciseTypeId)
}