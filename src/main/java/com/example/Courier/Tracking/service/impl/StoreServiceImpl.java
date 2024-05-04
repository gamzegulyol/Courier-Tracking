package com.example.Courier.Tracking.service.impl;

import com.example.Courier.Tracking.constant.ResponseMessage;
import com.example.Courier.Tracking.mapper.StoreMapper;
import com.example.Courier.Tracking.model.api.request.CreateStoreRequest;
import com.example.Courier.Tracking.model.api.response.CreateStoreResponse;
import com.example.Courier.Tracking.model.api.response.GetStoreResponse;
import com.example.Courier.Tracking.model.dto.StoreDto;
import com.example.Courier.Tracking.model.entity.Store;
import com.example.Courier.Tracking.repository.StoreRepository;
import com.example.Courier.Tracking.service.StoreService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    @Override
    public CreateStoreResponse createStore(CreateStoreRequest createStoreRequest) {
        Store store = Store.builder()
            .name(createStoreRequest.getName())
            .longitude(createStoreRequest.getLng())
            .latitude(createStoreRequest.getLat())
            .build();

        storeRepository.save(store);

        return CreateStoreResponse.builder()
            .code(HttpStatus.OK.name())
            .message(ResponseMessage.SUCCESS_MESSAGE)
            .build();
    }


    public GetStoreResponse getAllStores() {
        List<Store> storeList = storeRepository.findAll();

        List<StoreDto> responseDtoList = storeList.stream()
            .map(storeMapper::toStoreDto)
            .collect(Collectors.toList());

        return GetStoreResponse.builder()
            .code(HttpStatus.OK.name())
            .message(ResponseMessage.SUCCESS_MESSAGE)
            .data(responseDtoList)
            .build();
    }
}
