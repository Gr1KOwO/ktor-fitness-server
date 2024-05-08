package com.fitnessServIB.data.repository

import com.fitnessServIB.data.model.FoodTypeModel
import com.fitnessServIB.data.tables.FoodTypeTable
import com.fitnessServIB.domain.repository.FoodTypeRepository
import com.fitnessServIB.domain.usecase.FoodTypeUseCase
import com.fitnessServIB.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class FoodTypeRepositoryImpl(): FoodTypeRepository {
    override suspend fun getAllFoodTypes(): List<FoodTypeModel> = dbQuery {
        FoodTypeTable.selectAll().mapNotNull { rowToFoodType(it) }
    }

    override suspend fun getFoodTypeById(id: Int): FoodTypeModel? = dbQuery {
        FoodTypeTable.select { FoodTypeTable.id eq id }.mapNotNull { rowToFoodType(it) }.singleOrNull()
    }

    override suspend fun createFoodType(foodType: FoodTypeModel) {
        dbQuery {
            FoodTypeTable.insert { row ->
                row[name] = foodType.name
            }
        }
    }

    override suspend fun updateFoodType(foodType: FoodTypeModel) {
        dbQuery {
            FoodTypeTable.update({ FoodTypeTable.id eq foodType.id }) { row ->
                row[name] = foodType.name
            }
        }
    }

    override suspend fun deleteFoodType(id: Int) {
        dbQuery {
            FoodTypeTable.deleteWhere { FoodTypeTable.id eq id }
        }
    }

    private fun rowToFoodType(row: ResultRow?): FoodTypeModel? {
        if (row == null) return null
        return FoodTypeModel(
            id = row[FoodTypeTable.id],
            name = row[FoodTypeTable.name],
        )
    }
}