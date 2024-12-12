package org.problem2.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.problem2.dto.comment.CommentResponseDTO;
import org.problem2.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponseDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReadDTO {
        private Long id;
        private String title;
        private String writer;
        private String body;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
        private List<CommentResponseDTO.ReadListDTO> commentList;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReadListDTO {
        private Long id;
        private String title;
        private String writer;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
        private Integer commentCount;
    }
}
