package org.problem2.comment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.problem2.dto.comment.CommentRequestDTO;
import org.problem2.entity.Comment;
import org.problem2.entity.Post;
import org.problem2.entity.User;
import org.problem2.repository.CommentRepository;
import org.problem2.repository.PostRepository;
import org.problem2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTests {

    private static final String BASE_URL = "/comments";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    public static void printResult(MvcResult result) throws UnsupportedEncodingException {
        String responseContent = result.getResponse().getContentAsString();
        int responseStatus = result.getResponse().getStatus();

        System.out.println("응답 본문: " + responseContent);
        System.out.println("HTTP 상태 코드: " + responseStatus);
    }

    @Test
    @DisplayName("[POST] /comments?postId={postId}")
    public void testCreate() throws Exception {
        List<Post> postList = postRepository.findAll();
        if (postList.isEmpty()) {
            System.out.println("게시글 데이터가 존재하지 않습니다.");
            return;
        }
        Long postId = postList.get(0).getId();
        User user = userRepository.findByName("Kim1");
        String body = "Test Body";
        String requestBody = objectMapper.writeValueAsString(
                CommentRequestDTO.CreateDTO.builder()
                        .writer(user.getName())
                        .body(body)
                        .build()
        );
        MvcResult result = mockMvc.perform(post(BASE_URL + "?postId={postId}", postId)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        printResult(result);
    }

    @Test
    @DisplayName("[GET] /comments?postId={postId}")
    public void testReadList() throws Exception {
        List<Post> postList = postRepository.findAll();
        if (postList.isEmpty()) {
            System.out.println("게시글 데이터가 존재하지 않습니다.");
            return;
        }
        Long postId = postList.get(0).getId();

        // 테스트
        try {
            MvcResult result = mockMvc.perform(get(BASE_URL + "?postId={postId}", postId))
                    .andReturn();

            printResult(result);
        } catch (Exception e) {
            System.out.println("ID가 " + postId + "인 댓글이 존재하지 않습니다.");
        }
    }

    @Test
    @DisplayName("[PUT] /comments?postId={postId}")
    public void testUpdate() throws Exception {
        List<Comment> commentList = commentRepository.findAll();
        if (commentList.isEmpty()) {
            System.out.println("댓글 데이터가 존재하지 않습니다.");
            return;
        }
        Comment comment = commentList.get(0);
        User user = comment.getWriter();
        String updateBody = "Test Update Body";
        String requestBody = objectMapper.writeValueAsString(
                CommentRequestDTO.UpdateDTO.builder()
                        .id(comment.getId())
                        .postId(comment.getPost().getId())
                        .writer(user.getName())
                        .body(updateBody)
                        .build()
        );
        try {
            MvcResult result = mockMvc.perform(put(BASE_URL + "?postId={postId}", comment.getPost().getId())
                            .content(requestBody)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            printResult(result);
        } catch (Exception e) {
            System.out.println("ID가 " + comment.getId() + "인 댓글이 존재하지 않습니다.");
        }
    }

    @Test
    @DisplayName("[DELETE] /comments/{id}")
    public void testDelete() throws Exception {
        List<Comment> commentList = commentRepository.findAll();
        if (commentList.isEmpty()) {
            System.out.println("댓글 데이터가 존재하지 않습니다.");
            return;
        }
        Long id = commentList.get(0).getId();

        MvcResult result = mockMvc.perform(delete(BASE_URL + "/{id}", id))
                .andExpect(status().isOk())
                .andReturn();

        printResult(result);
    }

}
