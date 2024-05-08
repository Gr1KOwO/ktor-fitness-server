package com.fitnessServIB.data.tables

import org.jetbrains.exposed.sql.Table

object FoodTypeTable: Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 300)
    override val primaryKey = PrimaryKey(id)
}