package com.example.Courier.Tracking.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class CourierStoreEntryLogDto {

    private String courierName;
    private String courierSurname;
    private String storeName;
    private String entryTime;

}
