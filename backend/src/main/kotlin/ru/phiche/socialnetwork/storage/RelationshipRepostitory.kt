package ru.phiche.socialnetwork.storage

import ru.phiche.socialnetwork.api.dto.Relationship

interface RelationshipRepository {
    fun createFriendshipRequest(relationship: Relationship)
}