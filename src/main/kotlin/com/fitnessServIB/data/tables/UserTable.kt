package com.fitnessServIB.data.tables

import org.jetbrains.exposed.sql.Table

object UserTable: Table() {
    val userId = integer("userId").autoIncrement()
    val email = varchar("email", 512).uniqueIndex()
    val fullName = varchar("fullName",150)
    val age = integer("age")
    val weight = float("weight")
    val height = float("height")
    val sex = varchar("sex",20)
    val password = varchar("password",512)
    override val primaryKey = PrimaryKey(userId)
}