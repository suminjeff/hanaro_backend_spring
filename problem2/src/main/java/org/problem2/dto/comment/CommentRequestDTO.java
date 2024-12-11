package org.problem2.dto.comment;

import lombok.*;

public class CommentRequestDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateDTO {
        private Long postId;
        private String writer;
        private String body;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateDTO {
        private Long id;
        private Long postId;
        private String writer;
        private String body;
    }
}
