package com.example.Courier.Tracking.model.api.response;

import com.example.Courier.Tracking.model.dto.CourierStoreEntryLogDto;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class GetCourierStoreEntryLogResponse extends BaseResponse {

    private List<CourierStoreEntryLogDto> data;
}
