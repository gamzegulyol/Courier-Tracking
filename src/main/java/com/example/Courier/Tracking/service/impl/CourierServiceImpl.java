package com.example.Courier.Tracking.service.impl;

import com.example.Courier.Tracking.constant.ResponseMessage;
import com.example.Courier.Tracking.exception.CourierTrackingException;
import com.example.Courier.Tracking.helper.DistanceCalculator;
import com.example.Courier.Tracking.mapper.CourierStoreEntryLogMapper;
import com.example.Courier.Tracking.model.api.request.CreateCourierRequest;
import com.example.Courier.Tracking.model.api.request.UpdateCourierLocationRequest;
import com.example.Courier.Tracking.model.api.response.CreateCourierResponse;
import com.example.Courier.Tracking.model.api.response.GetCourierStoreEntryLogResponse;
import com.example.Courier.Tracking.model.api.response.UpdateCourierLocationResponse;
import com.example.Courier.Tracking.model.dto.CourierStoreEntryLogDto;
import com.example.Courier.Tracking.model.entity.Courier;
import com.example.Courier.Tracking.model.entity.CourierLocation;
import com.example.Courier.Tracking.model.entity.CourierStoreEntryLog;
import com.example.Courier.Tracking.model.entity.Store;
import com.example.Courier.Tracking.service.CourierService;
import com.example.Courier.Tracking.service.repository.CourierLocationRepositoryService;
import com.example.Courier.Tracking.service.repository.CourierRepositoryService;
import com.example.Courier.Tracking.service.repository.CourierStoreEntryLogRepositoryService;
import com.example.Courier.Tracking.service.repository.StoreRepositoryService;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourierServiceImpl implements CourierService {

    private static final double STORE_RADIUS_METERS = 100.0;
    private static final int MINUTES_BEFORE_REENTRY = 1;

    private final CourierRepositoryService courierRepositoryService;
    private final StoreRepositoryService storeRepositoryService;
    private final CourierLocationRepositoryService courierLocationRepositoryService;
    private final CourierStoreEntryLogRepositoryService courierStoreEntryLogRepositoryService;
    private final CourierStoreEntryLogMapper mapper;


    @Override
    public CreateCourierResponse createCourier(CreateCourierRequest createCourierRequest) {

        Courier courier = Courier.builder()
            .name(createCourierRequest.getName())
            .surname(createCourierRequest.getSurname())
            .build();

        courierRepositoryService.save(courier);

        return CreateCourierResponse.builder()
            .code(HttpStatus.OK.name())
            .message(ResponseMessage.SUCCESS_MESSAGE)
            .build();

    }

    /**
     * Updates the location of the courier and checks if the courier has entered any stores within the given radius. If
     * the courier enters a store and has been within 100 meters of the store for at least 1 minute, it logs the entry
     * with the current timestamp.
     *
     * @param updateCourierLocationRequest The request containing the new location of the courier.
     * @return An UpdateCourierLocationResponse indicating the result of the update operation.
     * @throws CourierTrackingException if the courier with the given ID is not found.
     */
    @Override
    public UpdateCourierLocationResponse updateCourierLocation(
        UpdateCourierLocationRequest updateCourierLocationRequest) {

        Courier courier = courierRepositoryService.findById(updateCourierLocationRequest.getCourierId());

        CourierLocation courierLocation = CourierLocation.builder()
            .courier(courier)
            .latitude(updateCourierLocationRequest.getLat())
            .longitude(updateCourierLocationRequest.getLng())
            .build();

        courierLocationRepositoryService.save(courierLocation);

        List<Store> stores = storeRepositoryService.findAll();
        stores.forEach(store -> {
            double distance = DistanceCalculator.calculateDistance(store.getLatitude(), store.getLongitude(),
                updateCourierLocationRequest.getLat(), updateCourierLocationRequest.getLng());
            if (distance <= STORE_RADIUS_METERS && courierEnterStoreCheck(courier, store)) {
                logCourierEntry(courier, store, Instant.now());
            }
        });

        return UpdateCourierLocationResponse.builder()
            .code(HttpStatus.OK.name())
            .message(ResponseMessage.SUCCESS_MESSAGE)
            .build();
    }

    private boolean courierEnterStoreCheck(Courier courier, Store store) {
        CourierStoreEntryLog lastEntry = courierStoreEntryLogRepositoryService.findFirstByCourierAndStoreOrderByEntryTimeDesc(
            courier, store);
        return lastEntry == null
            || Duration.between(lastEntry.getEntryTime(), Instant.now()).toMinutes() > MINUTES_BEFORE_REENTRY;
    }

    private void logCourierEntry(Courier courier, Store store, Instant entryTime) {
        CourierStoreEntryLog entryLog = CourierStoreEntryLog.builder()
            .courier(courier)
            .store(store)
            .entryTime(entryTime)
            .build();
        courierStoreEntryLogRepositoryService.save(entryLog);
    }

    /**
     * Calculates the total travel distance for a given courier. At least two locations are required to calculate the
     * distance. If the courier has less than two locations, 0.0 distance is returned.
     *
     * @param courierId The ID of the courier for whom the total travel distance will be calculated.
     * @return The total travel distance for the courier.
     */
    @Override
    public double getTotalTravelDistance(Long courierId) {

        List<CourierLocation> locations = courierLocationRepositoryService.findByCourierIdOrderByCreationDatetimeAsc(
            courierId);
        double totalDistance = 0.0;

        for (int i = 0; i < locations.size() - 1; i++) {
            CourierLocation currentLocation = locations.get(i);
            CourierLocation nextLocation = locations.get(i + 1);
            totalDistance += DistanceCalculator.calculateDistance(currentLocation.getLatitude(),
                currentLocation.getLongitude(),
                nextLocation.getLatitude(), nextLocation.getLongitude());
        }

        return totalDistance;

    }

    @Override
    public GetCourierStoreEntryLogResponse getCourierStoreEntryLog(Long courierId) {

        List<CourierStoreEntryLog> courierStoreEntryLogs = courierStoreEntryLogRepositoryService.findByCourierId(
            courierId);

        List<CourierStoreEntryLogDto> responseDtoList = courierStoreEntryLogs.stream()
            .map(mapper::toCourierStoreEntryLogDto)
            .collect(Collectors.toList());

        return GetCourierStoreEntryLogResponse.builder()
            .code(HttpStatus.OK.name())
            .message(ResponseMessage.SUCCESS_MESSAGE)
            .data(responseDtoList)
            .build();
    }

}
