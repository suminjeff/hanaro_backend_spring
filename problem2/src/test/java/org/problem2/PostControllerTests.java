package org.problem2;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.problem2.controller.PostController;
import org.problem2.model.dto.PostRequestDTO;
import org.problem2.model.entity.User;
import org.problem2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PostControllerTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "/post";

    @Test
    @DisplayName("[POST] /post/create")
    public void testPostCreate() throws Exception {
        User user = userRepository.getUserByName("Kim1");
        String title = "Test Title";
        String body = "Test Body";

        String requestBody = objectMapper.writeValueAsString(
                PostRequestDTO.CreatePostDTO.builder()
                    .writer(user)
                    .title(title)
                    .body(body)
                    .build()
        );

        mockMvc.perform(post(BASE_URL + "/create")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("[POST] /post/update")
    public void testPostUpdate() throws Exception {
        User user = userRepository.getUserByName("Kim1");
        String title = "Test Update Title";
        String body = "Test Update Body";
        String requestBody = objectMapper.writeValueAsString(
                PostRequestDTO.UpdatePostDTO.builder()
                    .id(1L)
                    .writer(user)
                    .title(title)
                    .body(body)
                    .build()
        );
        mockMvc.perform(post(BASE_URL + "/update")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

}
