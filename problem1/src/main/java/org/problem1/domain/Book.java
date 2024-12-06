package org.problem1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Book {
    private Integer bno;
    private String title, author, publisher, description, isbn, borrowerId;
    private LocalDateTime startDate, endDate;
    private Boolean availability;
}
