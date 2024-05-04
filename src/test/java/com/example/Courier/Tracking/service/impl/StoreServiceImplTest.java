package com.example.Courier.Tracking.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.Courier.Tracking.constant.ResponseMessage;
import com.example.Courier.Tracking.mapper.StoreMapper;
import com.example.Courier.Tracking.model.api.request.CreateStoreRequest;
import com.example.Courier.Tracking.model.api.response.CreateStoreResponse;
import com.example.Courier.Tracking.model.api.response.GetStoreResponse;
import com.example.Courier.Tracking.model.dto.StoreDto;
import com.example.Courier.Tracking.model.entity.Store;
import com.example.Courier.Tracking.service.repository.StoreRepositoryService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class StoreServiceImplTest {

    @InjectMocks
    private StoreServiceImpl service;

    @Mock
    private StoreRepositoryService storeRepositoryService;


    @Mock
    private StoreMapper storeMapper;

    @Test
    public void should_Create_Store() {
        // Given
        CreateStoreRequest request = new CreateStoreRequest();
        request.setName("Migros");
        request.setLat(40.9923307);
        request.setLng(29.1244229);

        when(storeRepositoryService.save(any())).thenReturn(any());

        // When
        CreateStoreResponse response = service.createStore(request);

        // Then
        assertEquals(HttpStatus.OK.name(), response.getCode());
        assertEquals("SUCCESSFUL", response.getMessage());

        verify(storeRepositoryService, times(1)).save(any());
    }
    @Test
    public void should_Get_All_Stores() {
        List<Store> stores = Arrays.asList(new Store(), new Store());

        List<StoreDto> dtoList = stores.stream()
            .map(storeMapper::toStoreDto)
            .collect(Collectors.toList());

        when(storeRepositoryService.findAll()).thenReturn(stores);
        when(storeMapper.toStoreDto(stores.get(0))).thenReturn(dtoList.get(0));
        when(storeMapper.toStoreDto(stores.get(1))).thenReturn(dtoList.get(1));

        GetStoreResponse response = service.getAllStores();

        assertEquals(HttpStatus.OK.name(), response.getCode());
        assertEquals(ResponseMessage.SUCCESS_MESSAGE, response.getMessage());
        assertEquals(dtoList, response.getData());
    }



}

