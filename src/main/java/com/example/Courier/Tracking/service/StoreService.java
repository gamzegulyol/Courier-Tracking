package com.example.Courier.Tracking.service;

import com.example.Courier.Tracking.model.api.request.CreateStoreRequest;
import com.example.Courier.Tracking.model.api.response.CreateStoreResponse;
import com.example.Courier.Tracking.model.api.response.GetStoreResponse;

public interface StoreService {

    CreateStoreResponse createStore(CreateStoreRequest createStoreRequest);

    GetStoreResponse getAllStores();
}
