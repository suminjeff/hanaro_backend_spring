package org.problem2.comment;

import org.junit.jupiter.api.Test;
import org.problem2.entity.Comment;
import org.problem2.entity.User;
import org.problem2.repository.CommentRepository;
import org.problem2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;


@SpringBootTest
public class CommentRepositoryTests {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreate() {
        User user = userRepository.findByName("Kim1");
        Comment comment = Comment.builder()
                .body("Repository Test Body")
                .writer(user)
                .build();
        commentRepository.save(comment);
    }

    @Test
    public void testRead() {
        Long postId = 1L;
        try {
            List<Comment> commentList = commentRepository.findAllByPostId(postId);
            for (Comment comment: commentList) {
                System.out.print("{ " +
                        "id: " + comment.getId() + ", " + ", " +
                        "body: " + comment.getBody() + ", " +
                        "writer: " + comment.getWriter().getName() + ", " +
                        "createAt: " + comment.getCreateAt() + ", " +
                        "updateAt: " + comment.getUpdateAt() +
                        " }");
            }
        } catch (NoSuchElementException e) {
            System.out.println("ID가 " + postId + "인 게시글이 존재하지 않습니다");
        }
    }

    @Test
    public void testReadList() {
        List<Comment> commentList = commentRepository.findAll();
        System.out.print("[ ");
        for (Comment comment : commentList) {
            System.out.print("{ " +
                    "id: " + comment.getId() + ", " + ", " +
                    "body: " + comment.getBody() + ", " +
                    "writer: " + comment.getWriter().getName() + ", " +
                    "createAt: " + comment.getCreateAt() + ", " +
                    "updateAt: " + comment.getUpdateAt() +
                    " }, ");
        }
        System.out.println(" ]");
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        String updateTitle = "Update Title";
        String updateBody = "Update Body";

        try {
            Comment comment = commentRepository.findById(id).orElseThrow();
            Comment commentBuild = Comment.builder()
                    .id(comment.getId())
                    .body(updateBody)
                    .writer(comment.getWriter())
                    .build();
            commentRepository.save(commentBuild);
            Comment updatedComment = commentRepository.findById(id).orElseThrow();
            System.out.print("{ " +
                    "id: " + updatedComment.getId() + ", " + ", " +
                    "body: " + updatedComment.getBody() + ", " +
                    "writer: " + updatedComment.getWriter().getName() + ", " +
                    "createAt: " + updatedComment.getCreateAt() + ", " +
                    "updateAt: " + updatedComment.getUpdateAt() +
                    " }");
        } catch (NoSuchElementException e) {
            System.out.println("ID가 " + id + "인 댓글이 존재하지 않습니다");
        }
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        try {
            commentRepository.deleteById(id);
            System.out.println("삭제 성공");
        } catch (Exception e) {
            System.out.println("ID가 " + id + "인 댓글이 존재하지 않습니다");
        }
    }
}
