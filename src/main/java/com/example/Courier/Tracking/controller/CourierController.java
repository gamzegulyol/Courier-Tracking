package com.example.Courier.Tracking.controller;

import com.example.Courier.Tracking.constant.Path;
import com.example.Courier.Tracking.model.api.request.CreateCourierRequest;
import com.example.Courier.Tracking.model.api.request.UpdateCourierLocationRequest;
import com.example.Courier.Tracking.model.api.response.CreateCourierResponse;
import com.example.Courier.Tracking.model.api.response.GetCourierStoreEntryLogResponse;
import com.example.Courier.Tracking.model.api.response.UpdateCourierLocationResponse;
import com.example.Courier.Tracking.service.CourierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Path.BASE_PATH_COURIER)
public class CourierController {

    private final CourierService service;

    @PostMapping()
    public ResponseEntity<CreateCourierResponse> createCourier(
        @RequestBody @Valid final CreateCourierRequest createCourierRequest) {
        CreateCourierResponse createCourierResponse = service.createCourier(createCourierRequest);
        return ResponseEntity.ok(createCourierResponse);
    }

    @PostMapping("/location")
    public ResponseEntity<UpdateCourierLocationResponse> updateCourierLocation(
        @RequestBody @Valid final UpdateCourierLocationRequest updateCourierLocationRequest) {
        UpdateCourierLocationResponse updateCourierLocationResponse = service.updateCourierLocation(
            updateCourierLocationRequest);
        return ResponseEntity.ok(updateCourierLocationResponse);
    }

    @GetMapping("/{courierId}/total-distance")
    public ResponseEntity<Double> getTotalTravelDistance(@PathVariable Long courierId) {
        double totalDistance = service.getTotalTravelDistance(courierId);
        return new ResponseEntity<>(totalDistance, HttpStatus.OK);
    }

    @GetMapping("/{courierId}/entry-log")
    public ResponseEntity<GetCourierStoreEntryLogResponse> getCourierEntryLog(@PathVariable Long courierId) {
        GetCourierStoreEntryLogResponse getCourierStoreEntryLogResponse = service.getCourierStoreEntryLog(courierId);
        return ResponseEntity.ok(getCourierStoreEntryLogResponse);
    }

}
