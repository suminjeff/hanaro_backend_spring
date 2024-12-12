package org.problem2.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.problem2.dto.post.PostRequestDTO;
import org.problem2.entity.Post;
import org.problem2.entity.User;
import org.problem2.repository.PostRepository;
import org.problem2.repository.UserRepository;
import org.problem2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTests {

    private static final String BASE_URL = "/posts";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;

    public static void printResult(MvcResult result) throws UnsupportedEncodingException {
        String responseContent = result.getResponse().getContentAsString();
        int responseStatus = result.getResponse().getStatus();

        System.out.println("응답 본문: " + responseContent);
        System.out.println("HTTP 상태 코드: " + responseStatus);
    }

    @Test
    @DisplayName("[POST] /posts")
    public void testCreate() throws Exception {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            System.out.println("유저 데이터가 존재하지 않습니다.");
            return;
        }
        User user = userList.get(0);
        String title = "Controller Test Title";
        String body = "Controller Test Body";
        String requestBody = objectMapper.writeValueAsString(
                PostRequestDTO.CreateDTO.builder()
                    .writer(user.getName())
                    .title(title)
                    .body(body)
                    .build()
        );
        MvcResult result = mockMvc.perform(post(BASE_URL)
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        printResult(result);
    }

    @Test
    @DisplayName("[GET] /posts/{id}")
    public void testRead() throws Exception {
        List<Post> postList = postRepository.findAll();
        if (postList.isEmpty()) {
            System.out.println("게시글 데이터가 존재하지 않습니다.");
            return;
        }
        Post post = postList.get(0);
        Long id = post.getId();
        // 테스트
        try {
            MvcResult result = mockMvc.perform(get(BASE_URL + "/{id}", id))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data.id").value(post.getId()))
                    .andExpect(jsonPath("$.data.title").value(post.getTitle()))
                    .andExpect(jsonPath("$.data.body").value(post.getBody()))
                    .andExpect(jsonPath("$.data.writer").value(post.getWriter().getName()))
                    .andExpect(jsonPath("$.data.createAt").value(post.getCreateAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"))))
                    .andExpect(jsonPath("$.data.updateAt").value(post.getUpdateAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"))))
                    .andReturn();

            printResult(result);
        } catch (NoSuchElementException e) {
            System.out.println("ID가 " + id + "인 게시글이 존재하지 않습니다.");
        }
    }

    @Test
    @DisplayName("[GET] /posts")
    public void testReadList() throws Exception {
        MvcResult result = mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andReturn();

        printResult(result);
    }

    @Test
    @DisplayName("[PUT] /posts")
    public void testUpdate() throws Exception {
        List<Post> postList = postRepository.findAll();
        if (postList.isEmpty()) {
            System.out.println("게시글 데이터가 존재하지 않습니다.");
            return;
        }
        Post post = postList.get(0);
        Long id = post.getId();
        String title = "Test Update Title";
        String body = "Test Update Body";
        String requestBody = objectMapper.writeValueAsString(
                PostRequestDTO.UpdateDTO.builder()
                    .id(id)
                    .writer(post.getWriter().getName())
                    .title(title)
                    .body(body)
                    .build()
        );
        try {
            MvcResult result = mockMvc.perform(put(BASE_URL)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            printResult(result);
        } catch (NoSuchElementException e) {
            System.out.println("ID가 " + id + "인 게시글이 존재하지 않습니다.");
        }
    }

    @Test
    @DisplayName("[DELETE] /posts/{id}")
    public void testDelete() throws Exception {
        List<Post> postList = postRepository.findAll();
        if (postList.isEmpty()) {
            System.out.println("게시글 데이터가 존재하지 않습니다.");
            return;
        }
        Long id = postList.get(0).getId();

        MvcResult result = mockMvc.perform(delete(BASE_URL + "/{id}", id))
                .andExpect(status().isOk())
                .andReturn();

        printResult(result);
    }

}
