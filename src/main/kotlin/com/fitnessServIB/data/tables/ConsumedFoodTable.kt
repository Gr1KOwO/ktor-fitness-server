package com.fitnessServIB.data.tables

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

object ConsumedFoodTable : Table() {
    val id = integer("id").autoIncrement()
    val date = date("date")
    val userId = varchar("email", 512).references(UserTable.email, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val foodId = integer("foodId").references(FoodTable.id,onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE).nullable()
    val dishId = integer("dishId").references(DishTable.id,onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE).nullable()
    val quantity = integer("quantity")
    val calories = float("calories")
    override val primaryKey = PrimaryKey(id)
}