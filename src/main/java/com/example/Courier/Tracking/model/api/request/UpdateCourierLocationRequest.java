package com.example.Courier.Tracking.model.api.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UpdateCourierLocationRequest {

    @NotNull(message = "{request.validation.courierId.invalid}")
    private Long courierId;
    @NotNull(message = "{request.validation.latitude.invalid}")
    private double lat;
    @NotNull(message = "{request.validation.longitude.invalid}")
    private double lng;

}
