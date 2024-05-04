package com.example.Courier.Tracking.model.api.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class GetCourierStoreEntryLogRequest {

    @NotNull(message = "{request.validation.page-number.invalid}")
    private Integer page = 0;

    @Positive(message = "{request.validation.size.invalid}")
    private Integer size = 10;

    private Long courierId;

    private String storeName;

}
