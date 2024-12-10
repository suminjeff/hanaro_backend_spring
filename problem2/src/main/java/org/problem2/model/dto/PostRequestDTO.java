package org.problem2.model.dto;

import lombok.*;
import org.problem2.model.entity.User;

public class PostRequestDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreatePostDTO {
        private User writer;
        private String title;
        private String body;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdatePostDTO {
        private Long id;
        private User writer;
        private String title;
        private String body;
    }
}
