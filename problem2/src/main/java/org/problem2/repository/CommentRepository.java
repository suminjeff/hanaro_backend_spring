package org.problem2.repository;

import org.problem2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.post.id = :postId")
    List<Comment> findAllByPostId(Long postId);
}
