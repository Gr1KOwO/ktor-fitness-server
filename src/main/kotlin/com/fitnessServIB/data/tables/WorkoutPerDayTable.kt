package com.fitnessServIB.data.tables

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

object WorkoutPerDayTable : Table() {
    val id = integer("id").autoIncrement()
    val date = text("date")
    val exerciseComplexId = integer("exerciseComplexId").references(ExerciseComplexTable.id,onUpdate = ReferenceOption.CASCADE, onDelete = ReferenceOption.CASCADE).nullable()
    val customWorkoutId = integer("customWorkoutId").references(CustomWorkoutsTable.id, onUpdate = ReferenceOption.CASCADE, onDelete = ReferenceOption.CASCADE).nullable()
    val isCompleted = bool("isCompleted")
    val isSkipped = bool("isSkipped")
    override val primaryKey = PrimaryKey(id)
}