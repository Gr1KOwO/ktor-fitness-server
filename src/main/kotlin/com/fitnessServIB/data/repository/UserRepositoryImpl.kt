package com.fitnessServIB.data.repository

import com.fitnessServIB.data.model.UserModel
import com.fitnessServIB.domain.repository.UserRepository
import com.fitnessServIB.plugins.DatabaseFactory.dbQuery
import com.fitnessServIB.data.tables.UserTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.springframework.security.crypto.bcrypt.BCrypt

class UserRepositoryImpl:UserRepository {
    override suspend fun getUser(email: String): UserModel? {
        return dbQuery{
            UserTable.select{ UserTable.email.eq(email)}.map { rowToUser(it)  }.singleOrNull()
        }
    }
    override suspend fun insertUser(user: UserModel) {
         dbQuery {
            UserTable.insert { table->
                table[email] = user.email
                table[password] = BCrypt.hashpw(user.password, BCrypt.gensalt())
                table[sex] = user.sex
                table[weight] = user.weight
                table[height]= user.height
                table[age] = user.age
                table[fullName] = user.fullName
            }
        }
    }
    override suspend fun updateUser(user: UserModel) {
        dbQuery {
            UserTable.update({ UserTable.email.eq(user.email) }) { table->
                table[password] = user.password
                table[sex] = user.sex
                table[weight] = user.weight
                table[height] = user.height
                table[age] = user.age
                table[fullName] = user.fullName
            }
        }
    }
    private fun rowToUser(row: ResultRow?):UserModel?{
        if (row==null){return null}
        return UserModel(
            email = row[UserTable.email],
            password = row[UserTable.password],
            fullName = row[UserTable.fullName],
            height = row[UserTable.height],
            weight = row[UserTable.weight],
            sex = row[UserTable.sex],
            age = row[UserTable.age],
        )
    }
    override suspend fun deleteUser(email: String) {
        dbQuery {
            UserTable.deleteWhere { UserTable.email eq email }
        }
    }
}