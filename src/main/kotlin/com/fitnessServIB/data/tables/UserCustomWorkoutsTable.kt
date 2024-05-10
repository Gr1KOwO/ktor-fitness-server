package com.fitnessServIB.data.tables

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object UserCustomWorkoutsTable: Table() {
    val userId = integer("userId").references(UserTable.userId, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val customWorkoutsId = integer("customWorkoutsId").references(CustomWorkoutsTable.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    override val primaryKey = PrimaryKey(userId, customWorkoutsId)
}