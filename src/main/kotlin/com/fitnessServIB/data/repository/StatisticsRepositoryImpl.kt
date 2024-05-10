package com.fitnessServIB.data.repository

import com.fitnessServIB.data.model.StatisticsModel
import com.fitnessServIB.data.tables.StatisticsTable
import com.fitnessServIB.domain.repository.StatisticsRepository
import com.fitnessServIB.plugins.DatabaseFactory.dbQuery
import kotlinx.datetime.LocalDate
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


class StatisticsRepositoryImpl: StatisticsRepository {
    override suspend fun getStatisticsByUserAndDate(userId: Int, date: LocalDate): StatisticsModel? = dbQuery {
        StatisticsTable.select {
            (StatisticsTable.userId eq userId) and
                    (StatisticsTable.date eq date)
        }.mapNotNull { rowToStatistics(it) }.singleOrNull()
    }

    override suspend fun getAllStatisticsForUser(userId: Int): List<StatisticsModel> = dbQuery {
        StatisticsTable.select { StatisticsTable.userId.eq(userId) }.mapNotNull { rowToStatistics(it) }
    }
    override suspend fun createStatistics(statistics: StatisticsModel) {
        dbQuery {
            StatisticsTable.insert { row ->
                row[idStatistic] = statistics.idStatistic
                row[date] = statistics.date
                row[userId] = statistics.userId
                row[caloriesConsumed] = statistics.caloriesConsumed
                row[caloriesBurned] = statistics.caloriesSpent
            }
        }
    }

    override suspend fun updateStatistics(statistics: StatisticsModel) {
        dbQuery {
            StatisticsTable.update({ StatisticsTable.idStatistic eq statistics.idStatistic }) { row ->
                row[date] = statistics.date
                row[userId] = statistics.userId
                row[caloriesConsumed] = statistics.caloriesConsumed
                row[caloriesBurned] = statistics.caloriesSpent
            }
        }
    }

    override suspend fun deleteStatistics(id: Int) {
        dbQuery {
            StatisticsTable.deleteWhere { idStatistic eq id }
        }
    }

    private fun rowToStatistics(row: ResultRow?): StatisticsModel? {
        if(row==null) return null
        return StatisticsModel(
            idStatistic = row[StatisticsTable.idStatistic],
            userId = row[StatisticsTable.userId],
            date = row[StatisticsTable.date],
            caloriesSpent = row[StatisticsTable.caloriesBurned],
            caloriesConsumed =row[StatisticsTable.caloriesConsumed],
        )
    }
}
