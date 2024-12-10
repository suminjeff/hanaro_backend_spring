package org.problem2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.problem2.controller.PostController;
import org.problem2.model.dto.PostRequestDTO;
import org.problem2.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(PostController.class)
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class PostControllerTests {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    @DisplayName("[POST] /post/create")
//    public void testPostCreate() throws Exception {
//        PostRequestDTO.CreatePostDTO createPostDTO = PostRequestDTO.CreatePostDTO.builder()
//                .writer(new User(1L, "testUser")) // User 객체 직접 생성
//                .title("Test Title")
//                .body("Test Body Content")
//                .build();
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/post/create")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andExpect(jsonPath())
//    }

}
