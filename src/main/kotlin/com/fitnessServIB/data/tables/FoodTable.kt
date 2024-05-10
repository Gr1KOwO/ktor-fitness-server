package com.fitnessServIB.data.tables

import org.jetbrains.exposed.sql.Table

object FoodTable : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    val foodTypeId = integer("foodTypeId").references(FoodTypeTable.id)
    val calories = float("calories")
    override val primaryKey = PrimaryKey(id)
}