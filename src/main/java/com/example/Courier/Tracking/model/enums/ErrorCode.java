package com.example.Courier.Tracking.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    VALIDATION_ERROR_CODE("0001", "Invalid Field", HttpStatus.BAD_REQUEST),
    UNEXPECTED_ERROR_CODE("002", "Unexpected exception occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    COURIER_NOT_FOUND("003", "Courier not found.", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
