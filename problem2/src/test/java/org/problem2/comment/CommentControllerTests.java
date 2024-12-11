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
import org.problem2.entity.User;
import org.problem2.repository.CommentRepository;
import org.problem2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

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

    public static void printResult(MvcResult result) throws UnsupportedEncodingException {
        String responseContent = result.getResponse().getContentAsString();
        int responseStatus = result.getResponse().getStatus();

        System.out.println("응답 본문: " + responseContent);
        System.out.println("HTTP 상태 코드: " + responseStatus);
    }

    @Test
    @DisplayName("[POST] /comments/create")
    public void testCreate() throws Exception {
        Long postId = 1L;
        User user = userRepository.findByName("Kim1");
        String body = "Test Body";
        String requestBody = objectMapper.writeValueAsString(
                CommentRequestDTO.CreateDTO.builder()
                        .writer(user.getName())
                        .body(body)
                        .build()
        );
        MvcResult result = mockMvc.perform(post(BASE_URL + "/create/{postId}", postId)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        printResult(result);
    }

    @Test
    @DisplayName("[GET] /comments/read/{tno}")
    public void testRead() throws Exception {
        Long id = 1L;

        // 테스트
        try {
            MvcResult result = mockMvc.perform(get(BASE_URL + "/read/{id}", id))
                    .andReturn();

            printResult(result);
        } catch (Exception e) {
            System.out.println("ID가 " + id + "인 댓글이 존재하지 않습니다.");
        }
    }

    @Test
    @DisplayName("[GET] /comments/read")
    public void testListRead() throws Exception {
        MvcResult result = mockMvc.perform(get(BASE_URL + "/read"))
                .andExpect(status().isOk())
                .andReturn();

        printResult(result);
    }

    @Test
    @DisplayName("[PUT] /comments/update")
    public void testUpdate() throws Exception {
        Long id = 1L;
        User user = userRepository.findByName("Kim1");
        String title = "Test Update Title";
        String body = "Test Update Body";
        String requestBody = objectMapper.writeValueAsString(
                CommentRequestDTO.UpdateDTO.builder()
                        .id(id)
                        .writer(user.getName())
                        .body(body)
                        .build()
        );
        try {
            MvcResult result = mockMvc.perform(put(BASE_URL + "/update")
                            .content(requestBody)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            printResult(result);
        } catch (Exception e) {
            System.out.println("ID가 " + id + "인 댓글이 존재하지 않습니다.");
        }
    }

    @Test
    @DisplayName("[DELETE] /comments/delete")
    public void testDelete() throws Exception {
        Long id = 1L;

        MvcResult result = mockMvc.perform(delete(BASE_URL + "/delete/{id}", id))
                .andExpect(status().isOk())
                .andReturn();

        printResult(result);
    }

}
