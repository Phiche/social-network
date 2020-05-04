package ru.phiche.socialnetwork.storage

import ru.phiche.socialnetwork.dto.Person
import ru.phiche.socialnetwork.storage.mapper.PersonMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.time.LocalDate

@Repository
class PersonRepositoryImpl(val jdbcTemplate: NamedParameterJdbcOperations) : PersonRepository {

    companion object {
        private const val CREATE_NEW_PERSON = "insert into person() values ()"
        private const val UPDATE_PERSON = "update person set name = :name," +
                "surname = :surname," +
                "patronymic = :patronymic," +
                "birth_at = :birthAt," +
                "gender = :gender," +
                "city = :city," +
                "interests = :interests " +
                "where id = :id"
        private const val GET_PERSON_BY_ID = "select person.* from person where id = :id"
        private const val GET_PEOPLE = "select * from person"
        private const val GET_PERSON_BY_ACCOUNT_ID = "select * from person p join account a on p.id = a.person_id " +
                "where a.id = :id"
        private val personMapper = PersonMapper()
    }

    override fun createNew(): Int {
        try {
            val keyHolder = GeneratedKeyHolder()
            jdbcTemplate.update(
                    CREATE_NEW_PERSON,
                    MapSqlParameterSource(),
                    keyHolder
            )

            return keyHolder.key!!.toInt()
        } catch (e: Exception) {
            throw Exception("Query CREATE_NEW_PERSON exception", e)
        }
    }

    override fun updatePerson(person: Person) {
            val updatedRows = jdbcTemplate.update(
                    UPDATE_PERSON,
                    MapSqlParameterSource()
                            .addValue("id", person.id)
                            .addValue("name", person.name)
                            .addValue("surname", person.surname)
                            .addValue("patronymic", person.patronymic)
                            .addValue("birthAt", person.birthAt?.toTimestamp())
                            .addValue("gender", person.gender?.toString())
                            .addValue("city", person.city)
                            .addValue("interests", person.interests)
            )

            if (updatedRows == 0) throw Exception("Person with id = ${person.id}, doesn't exists!")
    }

    private fun LocalDate.toTimestamp(): Timestamp {
        return Timestamp.valueOf(this.atStartOfDay())
    }

    override fun getPersonById(personId: Int): Person {
        return jdbcTemplate.query(GET_PERSON_BY_ID,
                MapSqlParameterSource()
                        .addValue("id", personId), personMapper)
                .firstOrNull() ?: throw Exception("Can't find person with id = $personId")
    }

    override fun getPersonByAccountId(accountId: Int): Person {
        return jdbcTemplate.query(GET_PERSON_BY_ACCOUNT_ID, MapSqlParameterSource()
                .addValue("id", accountId), personMapper)
                .firstOrNull() ?: throw Exception("Can't find person with accountId = $accountId")
    }

    override fun getPeople(): List<Person> {
        return jdbcTemplate.query(GET_PEOPLE, personMapper)
    }
}