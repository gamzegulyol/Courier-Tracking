package com.example.Courier.Tracking.service;

import com.example.Courier.Tracking.model.api.request.CreateCourierRequest;
import com.example.Courier.Tracking.model.api.request.GetCourierStoreEntryLogRequest;
import com.example.Courier.Tracking.model.api.request.UpdateCourierLocationRequest;
import com.example.Courier.Tracking.model.api.response.CreateCourierResponse;
import com.example.Courier.Tracking.model.api.response.GetCourierStoreEntryLogResponse;
import com.example.Courier.Tracking.model.api.response.UpdateCourierLocationResponse;
import org.springframework.data.domain.Pageable;

public interface CourierService {

    CreateCourierResponse createCourier(CreateCourierRequest createCourierRequest);

    UpdateCourierLocationResponse updateCourierLocation(UpdateCourierLocationRequest updateCourierLocationRequest);

    double getTotalTravelDistance(Long courierId);

    GetCourierStoreEntryLogResponse getCourierStoreEntryLog(Long courierId);
}
