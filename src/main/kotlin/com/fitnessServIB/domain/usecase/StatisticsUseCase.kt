package com.fitnessServIB.domain.usecase

import com.fitnessServIB.data.model.StatisticsModel
import com.fitnessServIB.domain.repository.StatisticsRepository
import kotlinx.datetime.LocalDate


class StatisticsUseCase(private val statisticsRepository: StatisticsRepository) {
    suspend fun getStatisticsByUserAndDate(userId: Int, date: LocalDate): StatisticsModel? {
        return statisticsRepository.getStatisticsByUserAndDate(userId, date)
    }

    suspend fun getAllStatisticsForUser(userId: Int): List<StatisticsModel> {
        return statisticsRepository.getAllStatisticsForUser(userId)
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