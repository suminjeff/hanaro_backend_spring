package org.problem2.dto.general.apiResponse;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ResponseDTO<T> {
    private final ReasonDTO reason;
    private final T data;

    @Getter
    @Builder
    public static class ReasonDTO {
        private final HttpStatus httpStatus;
        private final String statusCode;
        private final Boolean isSuccess;
        private final String message;
    }
}
