package com.example.Courier.Tracking.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.Courier.Tracking.TestUtils.TestUtils;
import com.example.Courier.Tracking.constant.Path;
import com.example.Courier.Tracking.model.api.request.CreateCourierRequest;
import com.example.Courier.Tracking.model.api.request.UpdateCourierLocationRequest;
import com.example.Courier.Tracking.model.api.response.CreateCourierResponse;
import com.example.Courier.Tracking.model.api.response.UpdateCourierLocationResponse;
import com.example.Courier.Tracking.service.CourierService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class CourierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourierService courierService;

    @Test
    public void should_Create_Courier() throws Exception {
        CreateCourierRequest request = CreateCourierRequest.builder()
            .name("Abc")
            .surname("Abc")
            .build();

        CreateCourierResponse response = new CreateCourierResponse();

        when(courierService.createCourier(request)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post(Path.BASE_PATH_COURIER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.convertObjectToJson(request)))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void should_Update_Courier_Location() throws Exception {
        UpdateCourierLocationRequest request = UpdateCourierLocationRequest.builder()
            .courierId(1L)
            .lat(2)
            .lng(3)
            .build();

        UpdateCourierLocationResponse response = new UpdateCourierLocationResponse();

        when(courierService.updateCourierLocation(request)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post(Path.BASE_PATH_COURIER + "/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.convertObjectToJson(request)))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void should_Get_Total_Travel_Distance() throws Exception {
        Long courierId = 1L;
        double distance = 100.0;

        when(courierService.getTotalTravelDistance(courierId)).thenReturn(distance);

        mockMvc.perform(MockMvcRequestBuilders.get(Path.BASE_PATH_COURIER + "/{courierId}/total-distance", courierId))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void should_Courier_Entry_Log() throws Exception {
        Long courierId = 1L;

        when(courierService.getCourierStoreEntryLog(courierId)).thenReturn(any());

        mockMvc.perform(MockMvcRequestBuilders.get(Path.BASE_PATH_COURIER + "/{courierId}/entry-log", courierId))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

}