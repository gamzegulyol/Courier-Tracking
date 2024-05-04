package com.example.Courier.Tracking.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.Courier.Tracking.TestUtils.TestUtils;
import com.example.Courier.Tracking.constant.Path;
import com.example.Courier.Tracking.model.api.request.CreateStoreRequest;
import com.example.Courier.Tracking.model.api.response.CreateStoreResponse;
import com.example.Courier.Tracking.model.api.response.GetStoreResponse;
import com.example.Courier.Tracking.service.StoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService service;


    @Test
    public void should_Create_Store() throws Exception {
        CreateStoreRequest request = CreateStoreRequest.builder()
            .name("Abc")
            .lat(1)
            .lng(2)
            .build();

        CreateStoreResponse response = new CreateStoreResponse();

        when(service.createStore(request)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post(Path.BASE_PATH_STORE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.convertObjectToJson(request)))
            .andExpect(status().isOk());
    }

    @Test
    public void should_Get_Store() throws Exception {

        GetStoreResponse response = new GetStoreResponse();

        when(service.getAllStores()).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get(Path.BASE_PATH_STORE))
            .andExpect(status().isOk());

    }

}