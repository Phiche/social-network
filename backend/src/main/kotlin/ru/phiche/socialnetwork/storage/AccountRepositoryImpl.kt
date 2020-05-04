package ru.phiche.socialnetwork.storage

import ru.phiche.socialnetwork.storage.mapper.Account
import ru.phiche.socialnetwork.storage.mapper.AccountMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.time.LocalDateTime

@Repository
class AccountRepositoryImpl(
        val jdbcTemplate: NamedParameterJdbcOperations
) : AccountRepository {

    companion object {
        private const val GET_ACCOUNT_BY_LOGIN = "select * from account where login = :login"
        private const val CREATE_ACCOUNT = "insert into account(person_id, login, password, registration_mail, registration_tel_no, registered_at)" +
                " values(:personId, :login, :password, :registrationMail, :registrationTelNo, :registeredAt)"
        private val accountMapper = AccountMapper()
    }

    override fun findAccountByLogin(login: String): Account? {
        try {
            return jdbcTemplate.query(GET_ACCOUNT_BY_LOGIN, MapSqlParameterSource()
                    .addValue("login", login), accountMapper).firstOrNull()
        } catch (e: Exception) {
            throw Exception("Query GET_ACCOUNT_BY_LOGIN exception", e)
        }
    }

    override fun createAccount(account: Account): Int {
        val keyHolder = GeneratedKeyHolder()
        with(account) {
            jdbcTemplate.update(CREATE_ACCOUNT, MapSqlParameterSource()
                    .addValue("personId", personId)
                    .addValue("login", login)
                    .addValue("password", BCryptPasswordEncoder().encode(password))
                    .addValue("registrationMail", registrationMail)
                    .addValue("registrationTelNo", registrationTelNo)
                    .addValue("registeredAt", Timestamp.valueOf(account.registeredAt)),//Timestamp.valueOf(LocalDateTime.now())),
                    keyHolder
            )

            return keyHolder.key!!.toInt()
        }
    }
}

/*    id INT NOT NULL AUTO_INCREMENT,
    person_id INT,
    login VARCHAR(100) CHARACTER SET utf8,
    password CHAR(60) NOT NULL,
    registration_mail VARCHAR(250) CHARACTER SET utf8,
    registration_tel_no VARCHAR(100),
    registered_at TIMESTAMP NOT NULL,*/