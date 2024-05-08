package com.fitnessServIB.data.repository

import com.fitnessServIB.data.model.DishModel
import com.fitnessServIB.data.tables.DishTable
import com.fitnessServIB.domain.repository.DishRepository
import com.fitnessServIB.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DishRepositoryImpl: DishRepository {
    override suspend fun getAllDishes(): List<DishModel>  = dbQuery {
        DishTable.selectAll().mapNotNull { rowToDish(it) }
    }

    override suspend fun getDishById(id: Int):DishModel? = dbQuery {
        DishTable.select { DishTable.id.eq(id) }.mapNotNull { rowToDish(it) }.singleOrNull()
    }

    override suspend fun createDish(dish: DishModel) {
        dbQuery {
            DishTable.insert { table ->
                table[name] = dish.name
                table[calories] = dish.calories
                table[description] = dish.description
                table[recipe] = dish.recipe
                table[videoLink] = dish.videoLink
            }
        }
    }

    override suspend fun updateDish(dish: DishModel) {
        dbQuery {
            DishTable.update({ DishTable.id eq dish.id }) { table ->
                table[name] = dish.name
                table[calories] = dish.calories
                table[description] = dish.description
                table[recipe] =dish.recipe
                table[videoLink] = dish.videoLink
            }
        }
    }

    override suspend fun deleteDish(id: Int) {
        dbQuery {
            DishTable.deleteWhere { DishTable.id eq id }
        }
    }
    private fun rowToDish(row: ResultRow?): DishModel? {
        if(row==null)return null
        return DishModel(
            id = row[DishTable.id],
            name = row[DishTable.name],
            calories = row[DishTable.calories],
            description = row[DishTable.description],
            recipe = row[DishTable.recipe],
            videoLink = row[DishTable.videoLink]
        )
    }
}