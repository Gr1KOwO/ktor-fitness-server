package com.fitnessServIB.plugins

import com.fitnessServIB.data.tables.*
import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    private val appConfig = HoconApplicationConfig(ConfigFactory.load())
    private val dbUrl = System.getenv("DB_POSTGRES_URL")
    private val dbUser = System.getenv("DB_POSTGRES_USER")
    private val dbPassword = System.getenv("DB_POSTGRES_PASSWORD")

    fun Application.initializationDataBase() {
        Database.connect(getHikariDatasource())
        transaction {
            SchemaUtils.create(
                ExerciseTypeTable,
                FoodTypeTable,
                FitnessLevelTable,
                FoodTable,
                ExerciseTable,
                ExerciseToTypeTable,
                UserTable,
                StatisticsTable,
                DishTable,
                ExerciseComplexTable,
                FitnessLevelExerciseComplexTable,
                UserExerciseComplexTable,
                CustomWorkoutsTable,
                UserCustomWorkoutsTable,
                WorkoutPerDayTable,
                ExercisesInWorkoutTable,
                ConsumedFoodTable
            )
        }
    }

    private fun getHikariDatasource():HikariDataSource{
        val config = HikariConfig()
        config.driverClassName = "org.postgresql.Driver"
        config.jdbcUrl = dbUrl
        config.username = dbUser
        config.password = dbPassword
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block:()->T):T=
        withContext(Dispatchers.IO){
            transaction { block() }
        }
}