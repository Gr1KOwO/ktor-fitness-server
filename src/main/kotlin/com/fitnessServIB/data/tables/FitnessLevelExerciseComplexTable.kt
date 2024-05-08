package com.fitnessServIB.data.tables

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object FitnessLevelExerciseComplexTable:Table() {
    val fitnessLevelId = integer("fitnessLevelId").references(FitnessLevelTable.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val exerciseComplexId = integer("exerciseComplexId").references(ExerciseComplexTable.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    override val primaryKey= PrimaryKey(fitnessLevelId,exerciseComplexId)
}