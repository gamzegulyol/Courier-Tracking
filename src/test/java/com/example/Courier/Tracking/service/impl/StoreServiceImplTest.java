package com.example.Courier.Tracking.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.Courier.Tracking.model.api.request.CreateStoreRequest;
import com.example.Courier.Tracking.model.api.response.CreateStoreResponse;
import com.example.Courier.Tracking.repository.StoreRepository;
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
    private StoreRepository storeRepository;

    @Test
    public void should_Create_Store() {
        // Given
        CreateStoreRequest request = new CreateStoreRequest();
        request.setName("Migros");
        request.setLat(40.9923307);
        request.setLng(29.1244229);

        when(storeRepository.save(any())).thenReturn(any());

        // When
        CreateStoreResponse response = service.createStore(request);

        // Then
        assertEquals(HttpStatus.OK.name(), response.getCode());
        assertEquals("SUCCESSFUL", response.getMessage());

        verify(storeRepository, times(1)).save(any());
    }

}

