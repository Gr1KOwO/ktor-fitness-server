package com.fitnessServIB.data.tables

import org.jetbrains.exposed.sql.Table

object DishTable : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 300)
    val description = text("description")
    val recipe = text("recipe")
    val videoLink = text("videoLink")
    val calories = float("calories")
    override val primaryKey = PrimaryKey(id)
}