package com.fitnessServIB.data.tables

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object ExercisesInWorkoutTable: Table() {
    val id = integer("id").autoIncrement()
    val workoutPerDayId = integer("workoutPerDayId").references(WorkoutPerDayTable.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val exerciseId = integer("exerciseId").references(ExerciseTable.id,onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val sets = integer("sets")
    val repetitions = integer("repetitions")
    val weight = integer("weight").nullable()
    val met = float("met")
    val isCompleted = bool("isCompleted")
    val isSkipped = bool("isSkipped")
    override val primaryKey = PrimaryKey(id)
}