package org.problem2.comment;

import org.junit.jupiter.api.Test;
import org.problem2.entity.Comment;
import org.problem2.entity.Post;
import org.problem2.entity.User;
import org.problem2.repository.CommentRepository;
import org.problem2.repository.PostRepository;
import org.problem2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class CommentRepositoryTests {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void testCreate() {
        List<Post> postList = postRepository.findAll();
        if (postList.isEmpty()) {
            System.out.println("게시글 데이터가 존재하지 않습니다.");
            return;
        }
        String body = "Repository Test Body";
        Long postId = postList.get(0).getId();
        try {
            User user = userRepository.findByName("Kim1");
            Post post = postRepository.findById(postId).orElseThrow();
            Comment comment = Comment.builder()
                    .body(body)
                    .post(post)
                    .writer(user)
                    .build();
            Comment savedComment = commentRepository.save(comment);

            assertNotNull(savedComment);
            assertEquals(body, savedComment.getBody());
            assertEquals(postId, savedComment.getPost().getId());
            assertEquals(user.getId(), savedComment.getWriter().getId());

        } catch (NoSuchElementException e) {
            System.out.println("ID가 " + postId + "인 게시글이 존재하지 않습니다");
        }
    }

    @Test
    public void testReadList() {
        List<Post> postList = postRepository.findAll();
        if (postList.isEmpty()) {
            System.out.println("게시글 데이터가 존재하지 않습니다.");
            return;
        }
        Long postId = postList.get(0).getId();
        List<Comment> commentList = commentRepository.findAllByPostId(postId);
        assertNotNull(commentList);
    }

    @Test
    public void testUpdate() {
        List<Comment> commentList = commentRepository.findAll();
        if (commentList.isEmpty()) {
            System.out.println("댓글 데이터가 존재하지 않습니다.");
            return;
        }
        Long id = commentList.get(0).getId();
        String updateBody = "Update Body";

        try {
            Comment comment = commentRepository.findById(id).orElseThrow();
            Comment commentBuild = Comment.builder()
                    .id(comment.getId())
                    .body(updateBody)
                    .post(comment.getPost())
                    .writer(comment.getWriter())
                    .build();
            commentRepository.save(commentBuild);
            Comment updatedComment = commentRepository.findById(id).orElseThrow();
            assertEquals(comment.getId(), updatedComment.getId());
            assertEquals(updateBody, updatedComment.getBody());
            assertEquals(comment.getPost().getId(), updatedComment.getPost().getId());
            assertEquals(comment.getWriter().getName(), updatedComment.getWriter().getName());

        } catch (NoSuchElementException e) {
            System.out.println("ID가 " + id + "인 댓글이 존재하지 않습니다");
        }
    }

    @Test
    public void testDelete() {
        List<Comment> commentList = commentRepository.findAll();
        if (commentList.isEmpty()) {
            System.out.println("댓글 데이터가 존재하지 않습니다.");
            return;
        }
        Long id = commentList.get(0).getId();
        try {
            commentRepository.deleteById(id);
            Comment deletedComment = commentRepository.findById(id).orElse(null);
            assertNull(deletedComment);
            System.out.println("삭제 성공");
        } catch (NoSuchElementException e) {
            System.out.println("ID가 " + id + "인 댓글이 존재하지 않습니다");
        }
    }
}
