package com.fitnessServIB.domain.repository

import com.fitnessServIB.data.model.StatisticsModel
import kotlinx.datetime.LocalDate


interface StatisticsRepository {
    suspend fun getStatisticsByUserAndDate(userEmail: String, date: LocalDate): StatisticsModel?
    suspend fun getAllStatisticsForUser(userEmail: String): List<StatisticsModel>
    suspend fun createStatistics(statistics: StatisticsModel)
    suspend fun updateStatistics(statistics: StatisticsModel)
    suspend fun deleteStatistics(id: Int)
}