package com.fitnessServIB.data.repository

import com.fitnessServIB.data.model.FoodModel
import com.fitnessServIB.data.tables.FoodTable
import com.fitnessServIB.domain.repository.FoodRepository
import com.fitnessServIB.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class FoodRepositoryImpl(): FoodRepository {
    override suspend fun getAllFoods(): List<FoodModel> = dbQuery {
        FoodTable.selectAll().mapNotNull { rowToFood(it) }
    }

    override suspend fun getFoodById(id: Int): FoodModel? {
        return dbQuery {
            FoodTable.select { FoodTable.id.eq(id) }.mapNotNull { rowToFood(it) }.singleOrNull()
        }
    }

    override suspend fun getFoodsByTypeId(typeId: Int): List<FoodModel> = dbQuery {
        FoodTable.select { FoodTable.foodTypeId.eq(typeId) }
            .mapNotNull { rowToFood(it) }
    }

    override suspend fun createFood(food: FoodModel) {
        dbQuery {
            FoodTable.insert { table ->
                table[name] = food.name
                table[foodTypeId] = food.foodTypeId
                table[calories] = food.calories
            }
        }
    }

    override suspend fun updateFood(food: FoodModel) {
        dbQuery {
            FoodTable.update({ FoodTable.id eq food.id }) { row ->
                row[name] = food.name
                row[foodTypeId] = food.foodTypeId
                row[calories] = food.calories
            }
        }
    }

    override suspend fun deleteFood(id: Int) {
        dbQuery {
            FoodTable.deleteWhere { FoodTable.id eq id }
        }
    }

    private fun rowToFood(row: ResultRow?): FoodModel? {
        if (row == null) return null
        return FoodModel(
            id = row[FoodTable.id],
            name = row[FoodTable.name],
            foodTypeId = row[FoodTable.foodTypeId],
            calories = row[FoodTable.calories]
        )
    }
}