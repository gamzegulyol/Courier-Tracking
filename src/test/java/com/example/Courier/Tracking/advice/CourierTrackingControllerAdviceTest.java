package com.example.Courier.Tracking.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.Courier.Tracking.exception.CourierTrackingException;
import com.example.Courier.Tracking.model.api.response.ErrorResponse;
import com.example.Courier.Tracking.model.enums.ErrorCode;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ExtendWith(MockitoExtension.class)
class CourierTrackingControllerAdviceTest {

    @InjectMocks
    private CourierTrackingControllerAdvice controllerAdvice;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private ObjectError objectError;

    @DisplayName("Tests for the Exception")
    class ExceptionTests {

        @Test
        void testException() {
            final Exception exception = new Exception();

            final ResponseEntity<ErrorResponse> responseEntity = controllerAdvice.handleException(exception);
            final ErrorResponse response = responseEntity.getBody();

            final ErrorCode errorCode = ErrorCode.UNEXPECTED_ERROR_CODE;

            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
            assert response != null;
            assertEquals(responseEntity.getStatusCode(), response.getHttpStatus());
            assertEquals(errorCode.getCode(), response.getCode());
            assertEquals(errorCode.getMessage(), response.getMessage());
        }
    }

    @Nested
    @DisplayName("Tests for the CourierTrackingException")
    class CourierTrackingExceptionTests {

        @Test
        void testCourierTrackingException() {
            final ErrorCode errorCode = ErrorCode.UNEXPECTED_ERROR_CODE;
            final CourierTrackingException exception = new CourierTrackingException(errorCode);

            final ResponseEntity<ErrorResponse> responseEntity = controllerAdvice.handleException(exception);
            final ErrorResponse response = responseEntity.getBody();

            assert response != null;
            assertEquals(responseEntity.getStatusCode(), response.getHttpStatus());
            assertEquals(errorCode.getCode(), response.getCode());
            assertEquals(errorCode.getMessage(), response.getMessage());
        }

    }

    @Nested
    @DisplayName("Tests for the MethodArgumentNotValidException")
    class MethodArgumentNotValidExceptionTests {

        @Test
        void testException() {
            final ErrorCode errorCode = ErrorCode.VALIDATION_ERROR_CODE;
            final String message = "def";

            when(methodArgumentNotValidException.getAllErrors()).thenReturn(List.of(objectError));
            when(objectError.getDefaultMessage()).thenReturn(message);

            final ResponseEntity<ErrorResponse> responseEntity = controllerAdvice.handleException(
                methodArgumentNotValidException);
            final ErrorResponse response = responseEntity.getBody();

            assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
            assert response != null;
            assertEquals(responseEntity.getStatusCode(), response.getHttpStatus());
            assertEquals(errorCode.getCode(), response.getCode());
            assertEquals(message, response.getMessage());
        }

    }

}