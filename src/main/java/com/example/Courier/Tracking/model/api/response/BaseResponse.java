package com.example.Courier.Tracking.model.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BaseResponse {
    @Builder.Default
    private HttpStatus httpStatus = HttpStatus.OK;
    private String code;
    private String message;

}
