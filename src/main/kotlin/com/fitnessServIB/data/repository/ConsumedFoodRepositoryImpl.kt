package com.fitnessServIB.data.repository

import com.fitnessServIB.data.model.ConsumedFoodModel
import com.fitnessServIB.data.tables.ConsumedFoodTable
import com.fitnessServIB.domain.repository.ConsumedFoodRepository
import com.fitnessServIB.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ConsumedFoodRepositoryImpl: ConsumedFoodRepository {
    override suspend fun getAllConsumedFood(userEmail: String): List<ConsumedFoodModel> = dbQuery {
        ConsumedFoodTable.select { ConsumedFoodTable.userId eq userEmail }.mapNotNull { rowToConsumedFood(it) }
    }

    override suspend fun getConsumedFoodById(id: Int): ConsumedFoodModel? = dbQuery {
        ConsumedFoodTable.select { ConsumedFoodTable.id.eq(id) }.mapNotNull { rowToConsumedFood(it) }.singleOrNull()
    }

    override suspend fun createConsumedFood(consumedFood: ConsumedFoodModel) {
        dbQuery {
            ConsumedFoodTable.insert { table ->
                table[foodId] = consumedFood.foodId
                table[userId] = consumedFood.userEmail
                table[date] = consumedFood.date
                table[quantity] = consumedFood.quantity
                table[dishId] = consumedFood.dishId
                table[calories] = consumedFood.calories
            }
        }
    }

    override suspend fun updateConsumedFood(consumedFood: ConsumedFoodModel) {
        dbQuery {
            ConsumedFoodTable.update({ ConsumedFoodTable.id eq consumedFood.id }) { table ->
                table[foodId] = consumedFood.foodId
                table[userId] = consumedFood.userEmail
                table[date] = consumedFood.date
                table[quantity] = consumedFood.quantity
                table[dishId] = consumedFood.dishId
                table[calories] = consumedFood.calories
            }
        }
    }

    override suspend fun deleteConsumedFood(id: Int) {
        dbQuery {
            ConsumedFoodTable.deleteWhere { ConsumedFoodTable.id eq id }
        }
    }

    private fun rowToConsumedFood(row: ResultRow?): ConsumedFoodModel? {
        if (row==null)return null
        return ConsumedFoodModel(
            id = row[ConsumedFoodTable.id],
            foodId = row[ConsumedFoodTable.foodId],
            userEmail = row[ConsumedFoodTable.userId],
            dishId = row[ConsumedFoodTable.foodId],
            quantity = row[ConsumedFoodTable.quantity],
            calories = row[ConsumedFoodTable.calories],
            date = row[ConsumedFoodTable.date]
        )
    }
}