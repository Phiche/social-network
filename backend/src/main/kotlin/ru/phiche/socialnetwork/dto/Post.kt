package ru.phiche.socialnetwork.dto

import java.time.LocalDateTime

data class Post (
        val id: Int? = null,
        val personId: Int? = null,
        val content: String? = null,
        val createdAt: LocalDateTime? = null
)