package com.fitnessServIB.data.tables

import org.jetbrains.exposed.sql.Table

object ExerciseTypeTable: Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    override val primaryKey= PrimaryKey(id)
}