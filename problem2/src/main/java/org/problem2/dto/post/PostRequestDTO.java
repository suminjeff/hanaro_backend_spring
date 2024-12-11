package org.problem2.dto.post;

import lombok.*;

public class PostRequestDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateDTO {
        private String writer;
        private String title;
        private String body;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateDTO {
        private Long id;
        private String writer;
        private String title;
        private String body;
    }
}
