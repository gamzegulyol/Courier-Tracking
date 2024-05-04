package com.example.Courier.Tracking.mapper;

import com.example.Courier.Tracking.model.dto.CourierStoreEntryLogDto;
import com.example.Courier.Tracking.model.entity.CourierStoreEntryLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourierStoreEntryLogMapper {


    @Mappings({
        @Mapping(source = "courier.name", target = "courierName"),
        @Mapping(source = "courier.surname", target = "courierSurname"),
        @Mapping(source = "store.name", target = "storeName"),
    })
    CourierStoreEntryLogDto toCourierStoreEntryLogDto(CourierStoreEntryLog source);
}
