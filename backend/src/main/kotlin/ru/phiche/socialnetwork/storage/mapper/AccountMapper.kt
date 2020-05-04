package ru.phiche.socialnetwork.storage.mapper

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.time.LocalDateTime

class AccountMapper : RowMapper<Account> {

    override fun mapRow(rs: ResultSet, rowNum: Int): Account = Account(
            id = rs.getInt("id"),
            personId = rs.getInt("person_id"),
            login = rs.getString("login"),
            password = rs.getString("password"),
            registrationMail = rs.getString("registration_mail"),
            registrationTelNo = rs.getString("registration_tel_no"),
            registeredAt = rs.getTimestamp("registered_At").toLocalDateTime()
    )
}

data class Account(
        @JsonProperty("id")
        val id: Int? = null,
        @JsonProperty("personId")
        val personId: Int? = null,
        @JsonProperty("login")
        val login: String? = null,
        @JsonProperty("password")
        val password: String? = null,
        @JsonProperty("registrationMail")
        val registrationMail: String? = null,
        @JsonProperty("registrationTelNo")
        val registrationTelNo: String? = null,
        @JsonProperty("registeredAt")
        val registeredAt: LocalDateTime? = null
)