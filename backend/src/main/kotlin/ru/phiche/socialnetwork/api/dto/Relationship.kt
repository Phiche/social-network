package ru.phiche.socialnetwork.api.dto

import java.time.LocalDateTime

data class Relationship (
        val parentPersonId: Int? = null,
        val childPersonId: Int? = null,
        val createdAt: LocalDateTime? = null
)