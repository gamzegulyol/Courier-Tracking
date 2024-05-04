package com.example.Courier.Tracking.model.api.response;

import com.example.Courier.Tracking.model.dto.StoreDto;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class GetStoreResponse extends BaseResponse {

    private List<StoreDto> data;
}
