package org.problem2.post;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.problem2.entity.Post;
import org.problem2.entity.User;
import org.problem2.repository.PostRepository;
import org.problem2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class PostRepositoryTests {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreate() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            System.out.println("유저 데이터가 존재하지 않습니다.");
            return;
        }
        String title = "Repository Test Title";
        String body = "Repository Test Body";
        User user = userList.get(0);
        Post post = Post.builder()
                .title(title)
                .body(body)
                .writer(user)
                .build();
        Post savedPost = postRepository.save(post);

        assertNotNull(savedPost);
        assertEquals(title, savedPost.getTitle());
        assertEquals(body, savedPost.getBody());
        assertEquals(user, savedPost.getWriter());
        System.out.println("게시글 생성 성공");
    }

    @Test
    public void testRead() {
        List<Post> postList = postRepository.findAll();
        if (postList.isEmpty()) {
            System.out.println("게시글 데이터가 존재하지 않습니다.");
            return;
        }
        Long id = postList.get(0).getId();
        try {
            Post post = postRepository.findById(id).orElseThrow();
            assertNotNull(post);
        } catch (NoSuchElementException e) {
            System.out.println("ID가 " + id + "인 게시글이 존재하지 않습니다");
        }
    }

    @Test
    public void testReadList() {
        List<Post> postList = postRepository.findAll();
        postList.forEach(Assertions::assertNotNull);
        System.out.println("게시글 목록 조회 성공");
    }

    @Test
    public void testUpdate() {
        List<Post> postList = postRepository.findAll();
        if (postList.isEmpty()) {
            System.out.println("게시글 데이터가 존재하지 않습니다.");
            return;
        }
        Long id = postList.get(0).getId();
        String updateTitle = "Update Title";
        String updateBody = "Update Body";
        try {
            Post post = postRepository.findById(id).orElseThrow();
            Post postBuild = Post.builder()
                    .id(post.getId())
                    .title(updateTitle)
                    .body(updateBody)
                    .writer(post.getWriter())
                    .build();
            postRepository.save(postBuild);
            Post updatedPost = postRepository.findById(id).orElseThrow();
            assertNotNull(updatedPost);
            assertEquals(updateTitle, updatedPost.getTitle());
            assertEquals(updateBody, updatedPost.getBody());
            assertEquals(post.getWriter().getName(), updatedPost.getWriter().getName());
        } catch (NoSuchElementException e) {
            System.out.println("ID가 " + id + "인 게시글이 존재하지 않습니다");
        }
    }

    @Test
    public void testDelete() {
        List<Post> postList = postRepository.findAll();
        if (postList.isEmpty()) {
            System.out.println("게시글 데이터가 존재하지 않습니다.");
            return;
        }
        Long id = postList.get(0).getId();
        try {
            postRepository.deleteById(id);
            Post deletedPost = postRepository.findById(id).orElse(null);
            assertNull(deletedPost);
            System.out.println("삭제 성공");
        } catch (NoSuchElementException e) {
            System.out.println("ID가 " + id + "인 게시글이 존재하지 않습니다");
        }
    }
}
