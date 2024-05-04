package com.example.Courier.Tracking.service.repository;

import com.example.Courier.Tracking.model.entity.Courier;
import com.example.Courier.Tracking.model.entity.CourierStoreEntryLog;
import com.example.Courier.Tracking.model.entity.Store;
import com.example.Courier.Tracking.repository.CourierStoreEntryLogRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CourierStoreEntryLogRepositoryService {

    private final CourierStoreEntryLogRepository repository;


    public CourierStoreEntryLog findFirstByCourierAndStoreOrderByEntryTimeDesc(Courier courier, Store store) {
        return repository.findFirstByCourierAndStoreOrderByEntryTimeDesc(courier, store);
    }

    public List<CourierStoreEntryLog> findByCourierId(Long courierId) {
        return repository.findByCourierId(courierId);
    }

    public CourierStoreEntryLog save(CourierStoreEntryLog courierStoreEntryLog) {
        return repository.save(courierStoreEntryLog);
    }


}
