package com.example.Courier.Tracking.service.repository;

import com.example.Courier.Tracking.model.entity.CourierLocation;
import com.example.Courier.Tracking.repository.CourierLocationRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CourierLocationRepositoryService {

    private final CourierLocationRepository repository;


    public CourierLocation save(CourierLocation courierLocation) {
        return repository.save(courierLocation);
    }

    public List<CourierLocation> findByCourierIdOrderByCreationDatetimeAsc(Long courierId) {
        return repository.findByCourierIdOrderByCreationDatetimeAsc(courierId);
    }


}
