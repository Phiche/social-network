package ru.phiche.socialnetwork.storage

import ru.phiche.socialnetwork.api.dto.Relationship

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository

@Repository
class RelationshipRepositoryImpl(val jdbcTemplate: NamedParameterJdbcOperations) : RelationshipRepository {

    companion object {
        private const val CREATE_RELATIONSHIP = "insert into relationship(parent_person_id, child_person_id, action_at) " +
                "values (:parentPersonId, :childPersonId, :createdAt)"
    }

    override fun createFriendshipRequest(relationship: Relationship) {
        jdbcTemplate.update(
                CREATE_RELATIONSHIP,
                MapSqlParameterSource()
                        .addValue("parentPersonId", relationship.parentPersonId)
                        .addValue("childPersonId", relationship.childPersonId)
                        .addValue("createdAt", relationship.createdAt)
        )
    }
}