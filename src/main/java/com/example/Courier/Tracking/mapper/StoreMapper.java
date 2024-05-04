package com.example.Courier.Tracking.mapper;

import com.example.Courier.Tracking.model.dto.StoreDto;
import com.example.Courier.Tracking.model.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoreMapper {


    StoreDto toStoreDto(Store source);
}
