package ru.phiche.socialnetwork.storage.mapper

import ru.phiche.socialnetwork.dto.Post
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class PostMapper : RowMapper<Post> {

    override fun mapRow(rs: ResultSet, rowNum: Int) = Post(
            id = rs.getInt("id"),
            personId = rs.getInt("person_id"),
            content = rs.getString("content"),
            createdAt = rs.getTimestamp("post_at")?.toLocalDateTime()
    )
}
