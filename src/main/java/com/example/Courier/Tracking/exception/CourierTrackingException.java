package com.example.Courier.Tracking.exception;

import com.example.Courier.Tracking.model.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourierTrackingException extends RuntimeException {

    private final ErrorCode errorCode;

    public CourierTrackingException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
