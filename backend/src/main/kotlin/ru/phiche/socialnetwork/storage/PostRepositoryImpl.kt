package ru.phiche.socialnetwork.storage

import ru.phiche.socialnetwork.dto.Post
import ru.phiche.socialnetwork.storage.mapper.PersonMapper
import ru.phiche.socialnetwork.storage.mapper.PostMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import java.lang.Exception

@Repository
class PostRepositoryImpl(val jdbcTemplate: NamedParameterJdbcOperations) : PostRepository {

    companion object {
        private const val CREATE_POST = "insert into post(person_id, content, post_at) values (:personId, :content, :postAt)"
        private const val UPDATE_POST = "update post set content = :content " +
                "where id = :id"
        private const val GET_POSTS_BY_PERSON_ID = "select * from post where person_id = :personId order by post_at desc"
        private val postMapper = PostMapper()
    }

    override fun createPost(post: Post): Int {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
                CREATE_POST,
                MapSqlParameterSource()
                        .addValue("personId", post.personId)
                        .addValue("content", post.content)
                        .addValue("postAt", post.createdAt),
                keyHolder
        )

        return keyHolder.key!!.toInt()
    }

    override fun updatePost(postId: Int) {
        val updatedRows = jdbcTemplate.update(UPDATE_POST, MapSqlParameterSource().addValue("id", postId))
        if (updatedRows == 0) throw Exception("Failed to update post with id = $postId")
    }

    override fun findPostsByPersonId(personId: Int): List<Post> {
        return jdbcTemplate.query(
                GET_POSTS_BY_PERSON_ID,
                MapSqlParameterSource().addValue("personId", personId),
                postMapper
        )
    }
}