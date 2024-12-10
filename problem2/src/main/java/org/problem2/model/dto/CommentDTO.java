package org.problem2.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDTO {
    private Long id;
    private String body;
}
