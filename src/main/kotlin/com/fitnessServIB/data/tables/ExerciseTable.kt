package com.fitnessServIB.data.tables

import org.jetbrains.exposed.sql.Table

object ExerciseTable: Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    val urlGif = text("urlGif").nullable()
    val description = text("description")
    val medicalContraindications = text("medicalContraindications")
    override val primaryKey = PrimaryKey(id)
}