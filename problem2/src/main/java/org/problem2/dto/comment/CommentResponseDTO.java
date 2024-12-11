package org.problem2.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CommentResponseDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReadListDTO {
        private Long id;
        private String writer;
        private String body;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
    }
}
