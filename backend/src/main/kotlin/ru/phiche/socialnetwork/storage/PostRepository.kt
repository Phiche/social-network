package ru.phiche.socialnetwork.storage

import ru.phiche.socialnetwork.dto.Post

interface PostRepository {
    fun createPost(post: Post): Int

    fun updatePost(postId: Int)

    fun findPostsByPersonId(personId: Int): List<Post>
}