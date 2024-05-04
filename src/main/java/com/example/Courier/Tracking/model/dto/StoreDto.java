package com.example.Courier.Tracking.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class StoreDto {

    private Long id;
    private String name;
    private String latitude;
    private String longitude;

}
