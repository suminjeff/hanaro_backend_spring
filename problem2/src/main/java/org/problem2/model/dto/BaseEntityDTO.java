package org.problem2.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class BaseEntityDTO {
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
