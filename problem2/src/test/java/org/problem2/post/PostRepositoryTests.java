package org.problem2.post;

import org.junit.jupiter.api.Test;
import org.problem2.entity.Post;
import org.problem2.entity.User;
import org.problem2.repository.PostRepository;
import org.problem2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;


@SpringBootTest
public class PostRepositoryTests {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreate() {
        User user = userRepository.findByName("Kim1");
        Post post = Post.builder()
                .title("Repository Test Title")
                .body("Repository Test Body")
                .writer(user)
                .build();
        postRepository.save(post);
    }

    @Test
    public void testRead() {
        Long id = 1L;
        try {
            Post post = postRepository.findById(id).orElseThrow();
            System.out.print("{ " +
                    "id: " + post.getId() + ", " + ", " +
                    "title: " + post.getTitle() + ", " +
                    "body: " + post.getBody() + ", " +
                    "writer: " + post.getWriter().getName() + ", " +
                    "createAt: " + post.getCreateAt() + ", " +
                    "updateAt: " + post.getUpdateAt() +
                    " }");
        } catch (NoSuchElementException e) {
            System.out.println("ID가 " + id + "인 게시글이 존재하지 않습니다");
        }
    }

    @Test
    public void testReadList() {
        List<Post> postList = postRepository.findAll();
        System.out.print("[ ");
        for (Post post : postList) {
            System.out.print("{ " +
                    "id: " + post.getId() + ", " + ", " +
                    "title: " + post.getTitle() + ", " +
                    "body: " + post.getBody() + ", " +
                    "writer: " + post.getWriter().getName() + ", " +
                    "createAt: " + post.getCreateAt() + ", " +
                    "updateAt: " + post.getUpdateAt() +
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
            Post post = postRepository.findById(id).orElseThrow();
            Post postBuild = Post.builder()
                    .id(post.getId())
                    .title(updateTitle)
                    .body(updateBody)
                    .writer(post.getWriter())
                    .build();
            postRepository.save(postBuild);
            Post updatedPost = postRepository.findById(id).orElseThrow();
            System.out.print("{ " +
                    "id: " + updatedPost.getId() + ", " + ", " +
                    "title: " + updatedPost.getTitle() + ", " +
                    "body: " + updatedPost.getBody() + ", " +
                    "writer: " + updatedPost.getWriter().getName() + ", " +
                    "createAt: " + updatedPost.getCreateAt() + ", " +
                    "updateAt: " + updatedPost.getUpdateAt() +
                    " }");
        } catch (NoSuchElementException e) {
            System.out.println("ID가 " + id + "인 게시글이 존재하지 않습니다");
        }
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        try {
            postRepository.deleteById(id);
            System.out.println("삭제 성공");
        } catch (Exception e) {
            System.out.println("ID가 " + id + "인 게시글이 존재하지 않습니다");
        }
    }
}
