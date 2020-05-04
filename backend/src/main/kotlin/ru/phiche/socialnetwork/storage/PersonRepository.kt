package ru.phiche.socialnetwork.storage

import ru.phiche.socialnetwork.dto.Person

interface PersonRepository {
    fun createNew(): Int

    fun updatePerson(person: Person)

    fun getPersonById(personId: Int): Person

    fun getPersonByAccountId(accountId: Int): Person

    fun getPeople(): List<Person>
}