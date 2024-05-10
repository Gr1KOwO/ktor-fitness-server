package com.fitnessServIB.data.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.*

object StatisticsTable: Table() {
    val idStatistic = integer("idStatistic").autoIncrement()
    val date = date("date").defaultExpression(CurrentDate)
    val userId = integer("userId").references(UserTable.userId)
    val caloriesConsumed = float("caloriesConsumed")
    val caloriesBurned = float("caloriesBurned")
    override val primaryKey = PrimaryKey(idStatistic)
}