package com.fitnessServIB.data.tables

import kotlinx.datetime.LocalDate
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

object ConsumedFoodTable : Table() {
    val id = integer("id").autoIncrement()
    val date = date("date")
    val userId = integer("userId").references(UserTable.userId, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val foodId = integer("foodId").references(FoodTable.id,onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE).nullable()
    val dishId = integer("dishId").references(DishTable.id,onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE).nullable()
    val quantity = integer("quantity")
    val calories = float("calories")
    override val primaryKey = PrimaryKey(id)
}