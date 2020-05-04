package ru.phiche.socialnetwork.storage.mapper

import ru.phiche.socialnetwork.dto.Gender
import ru.phiche.socialnetwork.dto.Person
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.time.LocalDate
import java.time.LocalDateTime

class PersonMapper : RowMapper<Person> {

    override fun mapRow(rs: ResultSet, rowNum: Int): Person = Person(
            id = rs.getLong("id"),
            name = rs.getString("name"),
            surname = rs.getString("surname"),
            patronymic = rs.getString("patronymic"),
            birthAt = rs.getTimestamp("birth_at")?.toLocalDateTime()?.toLocalDate(),
            gender = getGenderSafety(rs),
            city = rs.getString("city"),
            interests = rs.getString("interests")
    )

    private fun getGenderSafety(rs: ResultSet): Gender? {
        val genderString = rs.getString("gender")
        return if (genderString.isNullOrEmpty()) null
        else Gender.valueOf(rs.getString("gender"))
    }
}
