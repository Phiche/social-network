package ru.phiche.socialnetwork.dto

import java.time.LocalDate

data class Person (
        val id: Long? = null,
        val name: String? = null,
        val surname: String? = null,
        val patronymic: String? = null,
        val birthAt: LocalDate? = null,
        val gender: Gender? = null,
        val city: String? = null,
        val interests: String? = null
)

enum class Gender {
    MALE, FEMALE
}

