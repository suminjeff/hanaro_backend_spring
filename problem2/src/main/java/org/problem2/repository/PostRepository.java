package org.problem2.repository;

import org.problem2.entity.Comment;
import org.problem2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select count(c) from Comment c where c.post.id = :id")
    int countComments(Long id);

    @Query("select c from Comment c where c.post.id = :id")
    List<Comment> findComments(Long id);
}
