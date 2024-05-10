package com.fitnessServIB.data.tables

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object CustomWorkoutsTable: Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    val description = varchar("description", 255)
    val isPrivate = bool("isPrivate")
    override val primaryKey = PrimaryKey(id)
}