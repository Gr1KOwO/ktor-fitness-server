package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.StatisticsModel
import com.fitnessServIB.domain.repository.StatisticsRepository
import kotlinx.datetime.LocalDate


class StatisticsUseCase(private val statisticsRepository: StatisticsRepository) {
    suspend fun getStatisticsByUserAndDate(userEmail: String, date: LocalDate): StatisticsModel? {
        return statisticsRepository.getStatisticsByUserAndDate(userEmail, date)
    }

    suspend fun getAllStatisticsForUser(userEmail: String): List<StatisticsModel> {
        return statisticsRepository.getAllStatisticsForUser(userEmail)
    }

    suspend fun createStatistics(statistics: StatisticsModel) {
        statisticsRepository.createStatistics(statistics)
    }

    suspend fun updateStatistics(statistics: StatisticsModel) {
        statisticsRepository.updateStatistics(statistics)
    }

    suspend fun deleteStatistics(id: Int) {
        statisticsRepository.deleteStatistics(id)
    }
}