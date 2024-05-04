package com.example.Courier.Tracking.controller;

import com.example.Courier.Tracking.constant.Path;
import com.example.Courier.Tracking.model.api.request.CreateStoreRequest;
import com.example.Courier.Tracking.model.api.response.CreateStoreResponse;
import com.example.Courier.Tracking.model.api.response.GetStoreResponse;
import com.example.Courier.Tracking.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Path.BASE_PATH_STORE)
public class StoreController {

    private final StoreService service;

    @PostMapping()
    public ResponseEntity<CreateStoreResponse> createStore(
        @RequestBody @Valid final CreateStoreRequest createStoreRequest) {
        CreateStoreResponse createStoreResponse = service.createStore(createStoreRequest);
        return ResponseEntity.ok(createStoreResponse);
    }

    @GetMapping
    public ResponseEntity<GetStoreResponse> getAllStores() {
        GetStoreResponse getStoreResponse = service.getAllStores();
        return ResponseEntity.ok(getStoreResponse);
    }
}
