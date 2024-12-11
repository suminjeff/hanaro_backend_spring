package org.problem2.dto.general.apiResponse;

import org.springframework.http.HttpStatus;

public class ResponseBuilder {
    public static <T> ResponseDTO<T> success(HttpStatus httpStatus, T data, String message) {
        return ResponseDTO.<T>builder()
                .reason(ResponseDTO.ReasonDTO.builder()
                        .httpStatus(httpStatus)
                        .statusCode(String.valueOf(httpStatus.value()))
                        .isSuccess(true)
                        .message(message)
                        .build())
                .data(data)
                .build();
    }

    public static ResponseDTO<Void> failure(HttpStatus httpStatus, String message) {
        return ResponseDTO.<Void>builder()
                .reason(ResponseDTO.ReasonDTO.builder()
                        .httpStatus(httpStatus)
                        .statusCode(String.valueOf(httpStatus.value()))
                        .isSuccess(false)
                        .message(message)
                        .build())
                .build();
    }
}
