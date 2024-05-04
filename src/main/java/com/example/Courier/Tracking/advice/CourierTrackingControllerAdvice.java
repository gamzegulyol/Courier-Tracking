package com.example.Courier.Tracking.advice;

import com.example.Courier.Tracking.exception.CourierTrackingException;
import com.example.Courier.Tracking.model.api.response.ErrorResponse;
import com.example.Courier.Tracking.model.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class CourierTrackingControllerAdvice {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception ex) {
        log.error("Exception -> {}", ExceptionUtils.getStackTrace(ex));
        ErrorCode errorCode = ErrorCode.UNEXPECTED_ERROR_CODE;
        ErrorResponse errorResponse = ErrorResponse.builder()
            .code(errorCode.getCode())
            .message(errorCode.getMessage())
            .build();
        return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(final MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException -> {}", ExceptionUtils.getStackTrace(ex));
        ErrorCode errorCode = ErrorCode.VALIDATION_ERROR_CODE;
        ErrorResponse errorResponse = ErrorResponse.builder()
            .httpStatus(errorCode.getHttpStatus())
            .code(errorCode.getCode())
            .message(ex.getAllErrors().get(0).getDefaultMessage())
            .build();

        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }


    @ExceptionHandler(CourierTrackingException.class)
    public ResponseEntity<ErrorResponse> handleApiExceptions(final CourierTrackingException ex) {
        log.error("Courier Tracking Exception: {}", ExceptionUtils.getStackTrace(ex));
        ErrorResponse errorResponse = ErrorResponse.builder()
            .code(ex.getErrorCode().getCode())
            .message(ex.getErrorCode().getMessage())
            .build();
        return ResponseEntity.ok(errorResponse);
    }
}
