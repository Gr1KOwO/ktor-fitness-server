package com.fitnessServIB.data.tables

import org.jetbrains.exposed.sql.Table

object ExerciseComplexTable:Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 300)
    val description = text("description")
    override val primaryKey = PrimaryKey(id)
}