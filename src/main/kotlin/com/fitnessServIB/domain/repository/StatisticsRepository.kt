package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.StatisticsModel
import kotlinx.datetime.LocalDate


interface StatisticsRepository {
    suspend fun getStatisticsByUserAndDate(userId: Int, date: LocalDate): StatisticsModel?
    suspend fun getAllStatisticsForUser(userId: Int): List<StatisticsModel>
    suspend fun createStatistics(statistics: StatisticsModel)
    suspend fun updateStatistics(statistics: StatisticsModel)
    suspend fun deleteStatistics(id: Int)
}