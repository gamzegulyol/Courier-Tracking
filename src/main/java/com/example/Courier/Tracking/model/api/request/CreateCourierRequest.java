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
public class CreateCourierRequest {

    @NotNull(message = "{request.validation.name.invalid}")
    private String name;
    @NotNull(message = "{request.validation.surname.invalid}")
    private String surname;

}
